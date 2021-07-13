# weather-reporting-comparator

A framework to compare weather metrics like temperature, humidity and pressure in UI comparing with API by considering given variance.

#Library Used

Selenium, WebDriverManager, Rest Assured, Junit

#Environment Variables

We are taking all the data which can be changed often as an environment variable because it will be easy when we run this in pipeline.

export CITY="Bangalore"

export ALLOWED_VARIANCE_TEMP="5"

export ALLOWED_VARIANCE_HUMID="20"

export ALLOWED_VARIANCE_PRESSURE="100"

export API_KEY="7fe67bf08c80ded756e598d6f8fedaea"

Note: API_KEY variable should be masked (encrypted) when we have config in pipeline

#Running tests

Create an 'env.sh' file with above environment variables.

In terminal, 

run 'source env.sh' to source the environment variables

run 'gradle test'