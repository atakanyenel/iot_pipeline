from elasticsearch import Elasticsearch
from flask import Flask,jsonify
app = Flask(__name__)

es = Elasticsearch()
@app.route("/")
def hello():
    
    res=es.search(index="temperature",size=100, body={"query": {"match_all": {}}})
    return jsonify(res)


if __name__ == "__main__":
	app.run()

