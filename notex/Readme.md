# Industry Models - 'notex' - Jupyter Notebook / IPython Samples
The enclosed Jupyter Python Notebooks are samples to demonstrate the ability to extract a 
regulation, in this case ISO20022, from source eCore repository / XML into an intermediate
csv taxonomy representation and the subsequent loading of the csv into InfoSphere Governance 
Catalog (IGC), using IGC's Rest API feature. <br>

The notebooks contain introduction and other 'markdown' notes to demonstrate usage. The 
notebooks can be run in Jupyter Python environments e.g IBM's DsX / data science component 
of IBM's ICP4Data platform - but also using open source Jupyter Notebook.    

Below is a sample Dockerfile which demonstrates how to quickly create a running Jupyter notebook
docker container - including the PIP dependancies required for these notebooks.


```
FROM alpine:3.8

ENV NB_USER notex
ENV TOKEN notexdemo

USER root

RUN apk add --no-cache zeromq python3 libxml2 libxslt && \
    pip3 install --upgrade pip

RUN apk add --no-cache --virtual build-dependencies zeromq-dev python3-dev zlib-dev libxml2-dev libxslt-dev build-base \
    && pip3 install --no-cache-dir \
    ipython==6.5.0 \
    jupyter \ 
    pandas \
    bs4 \
    ipywidgets \
    requests \
    pymongo \
    lxml \
    jupyterlab \
    csvdiff \
    && apk del build-dependencies

RUN adduser --disabled-password --gecos '' $NB_USER

RUN mkdir /home/$NB_USER/work -p \
    mkdir /home/$NB_USER/reference -p

ADD ./published/ /home/$NB_USER/reference
ADD ./published/ /home/$NB_USER/work
RUN chown -R $NB_USER:$NB_USER /home/$NB_USER

USER $NB_USER
WORKDIR /home/$NB_USER

CMD jupyter lab --ip=0.0.0.0 --no-browser --NotebookApp.token="$TOKEN" --NotebookApp.allow_origin="*"
```

Regards, Industry Models team, IBM

