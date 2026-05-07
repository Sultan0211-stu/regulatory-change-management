import chromadb

client = chromadb.Client()
collection = client.get_or_create_collection("regulatory_changes")

def add_document(doc_id: str, text: str, metadata: dict = {}):
    collection.add(documents=[text], ids=[doc_id], metadatas=[metadata])

def get_recommendations(query: str, n_results: int = 5) -> list:
    results = collection.query(query_texts=[query], n_results=n_results)
    return results.get("documents", [[]])[0]
