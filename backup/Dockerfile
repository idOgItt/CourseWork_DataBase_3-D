FROM alpine:latest

RUN apk add --no-cache bash curl postgresql-client tzdata cron

ENV TZ=Etc/UTC

COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
COPY backup.sh /usr/local/bin/backup.sh
COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh

RUN chmod +x /usr/local/bin/docker-entrypoint.sh \
    /usr/local/bin/wait-for-it.sh \
    /usr/local/bin/backup.sh

COPY backup-cron /etc/crontabs/root

RUN mkdir -p /var/log/cron

ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["crond", "-f", "-l", "2"]