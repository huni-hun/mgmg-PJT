import pandas
from numpy import dot
from numpy.linalg import norm
import json
import os.path

from db_conn import engineconn
from db_class import User, MusicGenre, Music

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
SECRET_FILE = os.path.join(BASE_DIR, 'secrets.json')
secrets = json.loads(open(SECRET_FILE).read())
FILE_PATH = secrets["FILE_PATH"]

engine = engineconn()
session = engine.sessionmaker()

def getMusicRecommendations(recommendMusicRequest):
    try:
        session.commit()
        
        user = session.query(User).filter(User.user_id==recommendMusicRequest.user_id).one()
        musicTaste = session.query(MusicGenre).filter(MusicGenre.user_no==user.user_no).all()
       
        datas = readMusicData(musicTaste)
        datas2 = datas.loc[:,['화', '기쁨', '슬픔', '평온', '공포', '기대', '창피', '피곤', '짜증', '사랑']]
        result = find_similar_musics(recommendMusicRequest.diary_result, datas2)
        music = session.query(Music).filter(Music.music_no==result[0][0]).one()
        
        return music
    except:
        return {"result": "null"}


def readMusicData(musicTaste):
    df = pandas.read_csv(FILE_PATH['music'], sep='|',index_col = '인덱스' ,encoding='utf-8')
    datas = df[(df['장르'] == musicTaste[0].music_genre_name)]

    for i in range(1,len(musicTaste)):
        datas = pandas.concat([datas,df[(df['장르'] == musicTaste[i].music_genre_name)]])

    return datas

def cosine_similarity(A, B):
  return dot(A, B)/(norm(A)*norm(B))

def find_similar_musics(input, datas):

    result = []

    for idx,row in datas.iterrows():
        matrix = row.to_numpy()
        cosine = cosine_similarity(matrix, input)
        result.append( (idx, cosine))

    result.sort(key= lambda r: r[1], reverse= True)

    return result