from typing import List
from fastapi import FastAPI
from pydantic import BaseModel

from recommendation import getMusicRecommendations
from predict import load_model_n_tokenizer, predict

app = FastAPI()

model, tok = load_model_n_tokenizer()

class RecommendMusicRequest(BaseModel):
    user_id: str
    diary_content: str

@app.post('/recommendation/music')
async def recommendMusic(recommendMusicRequest: RecommendMusicRequest):
    diary_result, diary_emotion = predict(recommendMusicRequest.diary_content, model, tok)
    result = getMusicRecommendations(recommendMusicRequest, diary_result)
    return result
    
    