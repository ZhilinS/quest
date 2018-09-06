FROM openjdk:9-jre-slim

COPY build/libs/* /opt/quiz-backend/libs/

CMD [\
    "java", \
    "-Xms64m", \
    "-Xmx128m", \
    "-Dspring.profiles.active=local", \
    "-classpath", \
    "opt/quiz-backend/libs/*", \
    "me.quiz.App" \
]
