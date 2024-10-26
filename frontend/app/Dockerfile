# 使用 Node.js v11.15.0 的基础镜像
FROM node:11.15.0 as builder

# 设置环境变量指定使用 Python 2
ENV PYTHON /usr/bin/python
WORKDIR '/build'
COPY app ./app
COPY resources ./resources
COPY third_party ./third_party

WORKDIR '/build/app'

RUN npm install
RUN npm rebuild node-sass
RUN npm run build

RUN ls /build/app/dist

FROM nginx
EXPOSE 80
COPY --from=builder /build/app/dist /usr/share/nginx/html


