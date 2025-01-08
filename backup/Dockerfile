FROM alpine:3.19

RUN apk update && apk add --no-cache bash curl postgresql tzdata dcron

ENV TZ=Etc/UTC

COPY backup.sh /usr/local/bin/backup.sh
COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh

RUN chmod +x /usr/local/bin/docker-entrypoint.sh \
    /usr/local/bin/backup.sh

COPY backup-cron /etc/crontabs/root

RUN mkdir -p /var/log/cron

ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["crond", "-f", "-l", "2"]