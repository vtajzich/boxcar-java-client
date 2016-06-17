package net.tajzich.boxcar.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.RequestPatternBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

/**
 * Created by vtajzich
 */
@Unroll
class BoxcarClientSpec extends Specification {

    @Shared
    WireMockServer wireMockServer

    BoxcarClient client

    void setupSpec() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8080))
        wireMockServer.start();
    }

    void setup() {

        WireMock.reset();

        client = new BoxcarClient("token", "http://localhost:8080")
    }

    void cleanSpec() {
        wireMockServer.stop();
    }

    def "should publish notification with correct body: #body"() {

        given:

        RequestPatternBuilder builder = postRequestedFor(urlEqualTo('/api/notifications')).withRequestBody(equalTo(body))

        Notification notification = new Notification(title, longMessage, sound, sourceName, iconUrl, openUrl)

        when:

        stubFor(post(urlEqualTo('/api/notifications')).willReturn(aResponse().withStatus(200)))

        client.send(notification)

        then:

        verify(builder)

        where:

        title   | longMessage      | sound          | sourceName   | iconUrl                                            | openUrl                                        | body
        "title" | "a long message" | null           | null         | null                                               | null                                           | "user_credentials=token&notification[title]=title&notification[long_message]=a+long+message"
        "title" | "a long message" | Sound.BeepSoft | null         | null                                               | null                                           | "user_credentials=token&notification[title]=title&notification[long_message]=a+long+message&notification[sound]=beep-soft"
        "title" | "a long message" | Sound.BeepSoft | "spock test" | null                                               | null                                           | "user_credentials=token&notification[title]=title&notification[long_message]=a+long+message&notification[sound]=beep-soft&notification[source_name]=spock+test"
        "title" | "a long message" | Sound.BeepSoft | "spock test" | new URL("http://some-website.com/pat/to/icon.png") | null                                           | "user_credentials=token&notification[title]=title&notification[long_message]=a+long+message&notification[sound]=beep-soft&notification[source_name]=spock+test&notification[icon_url]=http%3A%2F%2Fsome-website.com%2Fpat%2Fto%2Ficon.png"
        "title" | "a long message" | Sound.BeepSoft | "spock test" | new URL("http://some-website.com/pat/to/icon.png") | new URL("http://some-website.com/pat/to/open") | "user_credentials=token&notification[title]=title&notification[long_message]=a+long+message&notification[sound]=beep-soft&notification[source_name]=spock+test&notification[icon_url]=http%3A%2F%2Fsome-website.com%2Fpat%2Fto%2Ficon.png&notification[open_url]=http%3A%2F%2Fsome-website.com%2Fpat%2Fto%2Fopen"
        "title" | "a long message" | Sound.BeepSoft | "spock test" | null                                               | new URL("http://some-website.com/pat/to/open") | "user_credentials=token&notification[title]=title&notification[long_message]=a+long+message&notification[sound]=beep-soft&notification[source_name]=spock+test&notification[open_url]=http%3A%2F%2Fsome-website.com%2Fpat%2Fto%2Fopen"
        "title" | "a long message" | null           | "spock test" | null                                               | new URL("http://some-website.com/pat/to/open") | "user_credentials=token&notification[title]=title&notification[long_message]=a+long+message&notification[source_name]=spock+test&notification[open_url]=http%3A%2F%2Fsome-website.com%2Fpat%2Fto%2Fopen"
    }
}
