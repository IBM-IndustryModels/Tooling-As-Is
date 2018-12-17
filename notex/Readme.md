# Industry Models - 'notex' <br>
# Jupyter Notebook / IPython Samples
The enclosed Jupyter Python Notebooks are samples to demonstrate the ability to extract a 
regulation, in this case ISO20022, from source eCore repository XML into an intermediate
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

ADD ./ /home/$NB_USER/reference
ADD ./ /home/$NB_USER/work
RUN chown -R $NB_USER:$NB_USER /home/$NB_USER

USER $NB_USER
WORKDIR /home/$NB_USER

CMD jupyter lab --ip=0.0.0.0 --no-browser --NotebookApp.token="$TOKEN" --NotebookApp.allow_origin="*"
```

# Tooling-As-Is
Industry Models Additional Utilities and Samples
================================================

Note: the items in this project are being shared on an "as-is" basis. Users may copy and modify Source Components and Sample Materials for internal use only provided however that Licensee may not alter or delete any copyright information or notices contained in the Source Components or Sample Materials. IBM provides the Source Components and Sample Materials without obligation of support and "AS IS", WITH NO WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING THE WARRANTY OF TITLE, NON-INFRINGEMENT OR NON-INTERFERENCE AND THE IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.

There is no direct support offered for these components.

Regards, Industry Models Development Team, IBM
