# Author: Elliott Larsen
# Date:
# Description:

from fastapi import APIRouter
from database import SessionLocal
from models import Post

router = APIRouter(
    prefix="/forum/post"
)

@router.get("/list")
def post_list():
    db = SessionLocal()
    post_list = db.query(Post).order_by(Post.create_date.desc()).all()
    db.close()
    return post_list