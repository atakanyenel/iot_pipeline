from elasticsearch import Elasticsearch
from flask import Flask,jsonify
app = Flask(__name__)

es = Elasticsearch()
@app.route("/")
def hello():
    
    res=es.search(index="temperature",size=100, body={"query": {"match_all": {}}})
    return jsonify(res)

@app.route("/api/v1/<string:index>/<int:id>")
def get_doc(index,id):
	try:
		res=es.get(index=index, doc_type='doc', id=id)
		return jsonify(res)
	except:
		return "{}"
if __name__ == "__main__":
	app.run()

