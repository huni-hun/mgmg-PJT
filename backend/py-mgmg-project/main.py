from fastapi import FastAPI
from fastapi.responses import JSONResponse
from pydantic import BaseModel

from recommendation import getMusicRecommendations
from predict import load_model_n_tokenizer, predict


app = FastAPI()

model, tok = load_model_n_tokenizer()

class predictDiaryRequest(BaseModel):
    user_id: str
    diary_content: str

@app.post('/predict/diary')
async def predictDiary(predictDiaryRequest: predictDiaryRequest):
    try:
        diary_result, diary_emotion = predict(predictDiaryRequest.diary_content, model, tok)
        music_no = getMusicRecommendations(predictDiaryRequest, diary_result, diary_emotion)
        return JSONResponse({
        'music_no': music_no,
        'emotion': diary_emotion,
        'statusCode': 200,
        'message': "일기 분석에 성공하였습니다."
        })
    except:
        return JSONResponse({
        'statusCode': 401,
        'message': "일기 분석에 실패하였습니다."
        })


