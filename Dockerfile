FROM ubuntu:latest
LABEL authors="m4lin"

ENTRYPOINT ["top", "-b"]