import os
from flask import Flask
from flask_cors import CORS
from dotenv import load_dotenv
from routes.describe import describe_bp
from routes.recommend import recommend_bp

load_dotenv()

app = Flask(__name__)
CORS(app, resources={r"/*": {"origins": os.environ.get("CORS_ORIGIN", "http://localhost:3000")}})

app.register_blueprint(describe_bp, url_prefix="/api")
app.register_blueprint(recommend_bp, url_prefix="/api")

@app.route("/health", methods=["GET"])
def health_check():
    from flask import jsonify
    return jsonify({"status": "healthy", "service": "ai-microservice"}), 200

if __name__ == "__main__":
    debug = os.environ.get("FLASK_DEBUG", "false").lower() == "true"
    host = os.environ.get("FLASK_HOST", "127.0.0.1")
    app.run(host=host, port=5000, debug=debug)
