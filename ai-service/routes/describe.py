from flask import Blueprint, request, jsonify
from services.groq_client import get_description

describe_bp = Blueprint("describe", __name__)

@describe_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json()
    text = data.get("text", "")
    result = get_description(text)
    return jsonify({"description": result})
