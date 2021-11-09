FROM openjdk:8
COPY . .
EXPOSE 8099:8099
ENTRYPOINT ["java","-jar","target/TimesheetJenkinsTrialEdition-2.0.jar" ]