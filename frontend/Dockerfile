FROM node:21 AS builder
WORKDIR /app

COPY package.json yarn.lock ./
RUN yarn install --frozen-lockfile
COPY . .
RUN yarn build

FROM nginx:alpine

RUN apk add --no-cache bash gettext

COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

COPY --from=builder /app/dist /usr/share/nginx/html

COPY nginx.conf.template /etc/nginx/nginx.conf.template

ENTRYPOINT ["bash", "/usr/local/bin/docker-entrypoint.sh"]

CMD ["nginx", "-g", "daemon off;"]