#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
@Time   : 2025/10/21 14:15
@Author : caixiaorong01@outlook.com
@File   : core.py
"""
from fastapi import APIRouter

from app.pkg.response import Response, success

router = APIRouter()


@router.get("/health", response_model=Response)
def read_items(

) -> Response:
    """
    health
    """
    return success(data="OK")
