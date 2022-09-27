from sqlalchemy import Column, BigInteger, String
from sqlalchemy.orm import declarative_base

Base = declarative_base()

class Music(Base):
  __tablename__ = 'music'

  music_no = Column(BigInteger, primary_key=True, autoincrement=True)
  album_art = Column(String, nullable=False)
  album_name = Column(String, nullable=False)
  artist = Column(String, nullable=False)
  music_genre_name = Column(String, nullable=False)
  music_name = Column(String, nullable=False)
  release_date = Column(String, nullable=False)

class MusicGenre(Base):
  __tablename__ = 'music_genre'

  music_genre_no = Column(BigInteger, primary_key=True, autoincrement=True)
  music_genre_name = Column(String, nullable=False)
  user_no = Column(BigInteger, nullable=False)
  emotion_name = Column(String, nullable=False)

class User(Base):
  __tablename__ = 'user'

  user_no = Column(BigInteger, primary_key=True, autoincrement=True)
  user_id = Column(String, nullable=False)

class BadMusic(Base):
  __tablename__ = 'bad_music'

  bad_music_no = Column(BigInteger, primary_key=True, autoincrement=True)
  music_no = Column(BigInteger, nullable=False)
  user_no = Column(BigInteger, nullable=False)