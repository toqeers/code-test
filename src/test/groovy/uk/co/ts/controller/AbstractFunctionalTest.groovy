package uk.co.ts.controller

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification
import uk.co.ts.CodeTestApplication

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

@TestPropertySource(locations = "/application-funcTest.properties", properties = ['management.port=0', 'logging.path=build/logs'])
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = CodeTestApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractFunctionalTest extends Specification {

    @Autowired
    ConfigurableApplicationContext context

    public RESTClient restClient
    public ObjectMapper mapper
    public final JsonSlurper slurper = new JsonSlurper()
    public static final String WIREMOCK_HOST = "localhost"
    protected static WireMockServer wireMockServer;
    public static final int WIREMOCK_PORT_NUMBER = 8089

    protected static final Logger LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger

    def setup() {
        mapper = new ObjectMapper()

        def port = context.embeddedServletContainer.port
        def url = "http://localhost:${port}"
        restClient = new RESTClient(url)

        // do not perform normal HTTPBuilder JSON parsing as we
        // will do this manually with a JsonSlurper later
        restClient.parser.'application/json' = restClient.parser.'text/plain'
        restClient.handler.failure = restClient.handler.success

        wireMockServer = new WireMockServer(wireMockConfig().port(WIREMOCK_PORT_NUMBER))
        wireMockServer.start()
        WireMock.configureFor(WIREMOCK_HOST, WIREMOCK_PORT_NUMBER)


        LOGGER.setLevel(Level.INFO)
        def context = (LoggerContext) LoggerFactory.getILoggerFactory()
        def appender = new ListAppender<ILoggingEvent>() {
            @Override
            protected void append(ILoggingEvent e) {
                e.getMDCPropertyMap()
                // This has the side-effect of capturing the MDC info. Required if there is no other appender configured which uses the MDC info.
                super.append(e)
            }
        }
        appender.setName("testAppender")
        appender.setContext(context)
        appender.start()
        LOGGER.addAppender(appender)
    }

    void cleanup() {
        wireMockServer.stop()
        LOGGER.detachAppender("testAppender")
    }

}
