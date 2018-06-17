FROM node:carbon

ENV TINI_VERSION v0.17.0
ADD https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini /tini
RUN chmod +x /tini
ENTRYPOINT ["/tini", "--"]

WORKDIR /home/node/app
COPY package*.json ./
RUN npm i
COPY . .



EXPOSE 8080
# At the end, set the user to use when running this image
USER node
CMD [ "node", "server.js" ]