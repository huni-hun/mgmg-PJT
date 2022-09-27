from typing import List
from fastapi import FastAPI
from pydantic import BaseModel
from recommendation import getMusicRecommendations


app = FastAPI()

class RecommendMusicRequest(BaseModel):
    user_id: str
    diary_result: List[float]=[]
    diary_emotion: str
    

@app.post('/recommendation/music')
async def recommendMusic(recommendMusicRequest: RecommendMusicRequest):
    result = getMusicRecommendations(recommendMusicRequest)
    return result
    
    