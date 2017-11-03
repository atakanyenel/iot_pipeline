from elasticsearch import Elasticsearch
from flask import Flask,jsonify
app = Flask(__name__)

es = Elasticsearch()
@app.route("/")
def hello():
    
    res=es.get(index="temperature",doc_type="doc",id=1)
    return jsonify(res)


if __name__ == "__main__":
	app.run()

