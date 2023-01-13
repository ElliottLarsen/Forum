# Author: Elliott Larsen
# Date:
# Description:

from sqlalchemy import Column, Integer, String, Text, DateTime, ForeignKey
from sqlalchemy.orm import relationship
from database import Base

class Post(Base):
    """
    Represents a Post object.
    """
    __tablename__ = "post"

    id = Column(Integer, primary_key = True)
    subject = Column(String, nullable = False)
    content = Column(Text, nullable = False)
    create_date = Column(DateTime, nullable = False)

class Comment(Base):
    """
    Represents a Comment object.
    """
    __tablename__ = "comment"

    id = Column(Integer, primary_key = True)
    content = Column(Text, nullable = False)
    create_date = Column(DateTime, nullable = False)
    question_id = Column(Integer, ForeignKey("post.id"))
    post = relationship("Post", backref = "comments")