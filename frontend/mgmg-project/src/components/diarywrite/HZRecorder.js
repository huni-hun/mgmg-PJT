export function HZRecorder(stream, config) {
  config = config || {};
  config.sampleBits = config.sampleBits || 16;
  config.sampleRate = config.sampleRate || 16000;

  var context = new (window.webkitAudioContext || window.AudioContext)();
  var audioInput = context.createMediaStreamSource(stream);
  var createScript =
    context.createScriptProcessor || context.createJavaScriptNode;
  var recorder = createScript.apply(context, [4096, 1, 1]);

  var audioData = {
    size: 0,
    buffer: [],
    inputSampleRate: context.sampleRate,
    inputSampleBits: 16,
    outputSampleRate: config.sampleRate,
    oututSampleBits: config.sampleBits,
    input: function (data) {
      this.buffer.push(new Float32Array(data));
      this.size += data.length;
    },
    compress: function () {
      var data = new Float32Array(this.size);
      var offset = 0;
      for (var i = 0; i < this.buffer.length; i++) {
        data.set(this.buffer[i], offset);
        offset += this.buffer[i].length;
      }

      var compression = parseInt(this.inputSampleRate / this.outputSampleRate);
      var length = data.length / compression;
      var result = new Float32Array(length);
      var index = 0,
        j = 0;
      while (index < length) {
        result[index] = data[j];
        j += compression;
        index++;
      }
      return result;
    },
    encodeWAV: function () {
      var sampleRate = Math.min(this.inputSampleRate, this.outputSampleRate);
      var sampleBits = Math.min(this.inputSampleBits, this.oututSampleBits);
      var bytes = this.compress();
      var dataLength = bytes.length * (sampleBits / 8);
      var buffer = new ArrayBuffer(44 + dataLength);
      var data = new DataView(buffer);

      var channelCount = 1; //
      var offset = 0;

      var writeString = function (str) {
        for (var i = 0; i < str.length; i++) {
          data.setUint8(offset + i, str.charCodeAt(i));
        }
      };

      writeString("RIFF");
      offset += 4;

      data.setUint32(offset, 36 + dataLength, true);
      offset += 4;

      writeString("WAVE");
      offset += 4;

      writeString("fmt ");
      offset += 4;

      data.setUint32(offset, 16, true);
      offset += 4;

      data.setUint16(offset, 1, true);
      offset += 2;

      data.setUint16(offset, channelCount, true);
      offset += 2;

      data.setUint32(offset, sampleRate, true);
      offset += 4;

      data.setUint32(
        offset,
        channelCount * sampleRate * (sampleBits / 8),
        true
      );
      offset += 4;

      data.setUint16(offset, channelCount * (sampleBits / 8), true);
      offset += 2;

      data.setUint16(offset, sampleBits, true);
      offset += 2;

      writeString("data");
      offset += 4;

      data.setUint32(offset, dataLength, true);
      offset += 4;

      if (sampleBits === 8) {
        for (var i = 0; i < bytes.length; i++, offset++) {
          var s = Math.max(-1, Math.min(1, bytes[i]));
          var val = s < 0 ? s * 0x8000 : s * 0x7fff;
          val = parseInt(255 / (65535 / (val + 32768)));
          data.setInt8(offset, val, true);
        }
      } else {
        for (let i = 0; i < bytes.length; i++, offset += 2) {
          let s = Math.max(-1, Math.min(1, bytes[i]));
          data.setInt16(offset, s < 0 ? s * 0x8000 : s * 0x7fff, true);
        }
      }

      return new Blob([data], { type: "audio/wav" });
    },
  };

  this.start = function () {
    audioInput.connect(recorder);
    recorder.connect(context.destination);
  };

  this.stop = function () {
    recorder.disconnect();
  };

  this.getBlob = function () {
    this.stop();
    return audioData.encodeWAV();
  };

  this.play = function (audio) {
    audio.src = window.URL.createObjectURL(this.getBlob());
  };

  this.upload = function () {
    return this.getBlob();
  };

  recorder.onaudioprocess = function (e) {
    audioData.input(e.inputBuffer.getChannelData(0));
  };
}
