from flask import Blueprint, request, jsonify
from services.chroma_client import get_recommendations

recommend_bp = Blueprint("recommend", __name__)

@recommend_bp.route("/recommend", methods=["POST"])
def recommend():
    data = request.get_json()
    query = data.get("query", "")
    results = get_recommendations(query)
    return jsonify({"recommendations": results})
