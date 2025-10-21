from typing import Any, Dict, Optional


class Settings:
    API_V1_STR: str = "/api/v1"
    PROJECT_NAME: str = "MindVault"

    # Database settings
    DATABASE_URL: Optional[str] = "sqlite:///./db.db"


settings = Settings()
