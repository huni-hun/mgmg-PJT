import torch
from torch.utils.data import Dataset
import gluonnlp as nlp
import numpy as np
from bert import BERTClassifier
from bert import BERTDataset

from kobert.utils import get_tokenizer
from kobert.pytorch_kobert import get_pytorch_kobert_model

device = torch.device('cpu')

# Setting parameters
max_len = 256
batch_size = 16
warmup_ratio = 0.1
num_epochs = 20
max_grad_norm = 1
log_interval = 100
learning_rate = 1e-5

def load_model_n_tokenizer():
    # BERT 모델, Vocabulary 불러오기 필수
    bertmodel, vocab = get_pytorch_kobert_model()
    tokenizer = get_tokenizer()
    tok = nlp.data.BERTSPTokenizer(tokenizer, vocab, lower=False)

    model_path = './model/model_10_state_dict_20-epoch.pt'
    # 전체 모델을 통째로 불러옴, 클래스 선언 필수
    model = BERTClassifier(bertmodel, dr_rate=0.5).to(device)
    # state_dict를 불러 온 후, 모델에 저장
    model.load_state_dict(torch.load(model_path, map_location=device), strict=False)
    model.to(device)
    model.eval()
    return model, tok

def new_softmax(a):
    c = np.max(a)  # 최댓값
    exp_a = np.exp(a - c)  # 각각의 원소에 최댓값을 뺀 값에 exp를 취한다. (이를 통해 overflow 방지)
    sum_exp_a = np.sum(exp_a)
    y = (exp_a / sum_exp_a) * 100
    return np.round(y, 3)

def predict(predict_sentence, model, tok):
    data = [predict_sentence, '0']
    dataset_another = [data]

    another_test = BERTDataset(dataset_another, 0, 1, tok, max_len, True, False)
    test_dataloader = torch.utils.data.DataLoader(another_test, batch_size=batch_size, num_workers=5)

    model.eval()

    for batch_id, (token_ids, valid_length, segment_ids, label) in enumerate(test_dataloader):
        token_ids = token_ids.long().to(device)
        segment_ids = segment_ids.long().to(device)

        valid_length = valid_length

        out = model(token_ids, valid_length, segment_ids)

        for i in out:
            logits = i
            logits = logits.detach().cpu().numpy()
            probability = []
            logits = np.round(new_softmax(logits), 3).tolist()
            for logit in logits:
                probability.append(np.round(logit, 3))

            if np.argmax(logits) == 0:
                emotion = "화"
            elif np.argmax(logits) == 1:
                emotion = "기쁨"
            elif np.argmax(logits) == 2:
                emotion = '슬픔'
            elif np.argmax(logits) == 3:
                emotion = '평온'
            elif np.argmax(logits) == 4:
                emotion = '공포'
            elif np.argmax(logits) == 5:
                emotion = '기대'
            elif np.argmax(logits) == 6:
                emotion = '창피'
            elif np.argmax(logits) == 7:
                emotion = '피곤'
            elif np.argmax(logits) == 8:
                emotion = '짜증'
            elif np.argmax(logits) == 9:
                emotion = '사랑'

    return probability, emotion
