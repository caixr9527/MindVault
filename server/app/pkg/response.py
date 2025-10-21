#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Copyright [2025] [caixiaorong]
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
"""
@Time   : 2024/6/29 21:47
@Author : caixiaorong01@outlook.com
@File   : response.py
"""
from dataclasses import field, dataclass
from typing import Any

from app.pkg.http_code import HttpCode


@dataclass
class Response:
    """基础HTTP接口响应格式"""
    code: HttpCode = HttpCode.SUCCESS
    message: str = ""
    data: Any = field(default_factory=dict)


def success(data: Any = None):
    """成功响应"""
    return Response(code=HttpCode.SUCCESS, message="", data=data)


def fail(data: Any = None):
    """失败响应"""
    return Response(code=HttpCode.FAIL, message="", data=data)


def validate_error(errors: dict = None):
    """数据验证错误响应"""
    first_key = next(iter(errors))
    if first_key is not None:
        msg = errors.get(first_key)[0]
    else:
        msg = ""
    return Response(code=HttpCode.VALIDATE_ERROR, message=msg, data=errors)


def message(code: HttpCode = None, msg: str = ""):
    """基础消息响应"""
    return Response(code=code, message=msg, data={})


def success_message(msg: str = ""):
    """成功消息响应"""
    return message(code=HttpCode.SUCCESS, msg=msg)


def fail_message(msg: str = ""):
    """失败消息响应"""
    return message(code=HttpCode.FAIL, msg=msg)


def not_found_message(msg: str = ""):
    """未找到消息响应"""
    return message(code=HttpCode.NOT_FOUND, msg=msg)


def unauthorized_message(msg: str = ""):
    """未认证消息响应"""
    return message(code=HttpCode.UNAUTHORIZED, msg=msg)


def forbidden_message(msg: str = ""):
    """未授权消息响应"""
    return message(code=HttpCode.FORBIDDEN, msg=msg)
