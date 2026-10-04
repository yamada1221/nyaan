package com.yaoroz.nyaan.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaoroz.nyaan.bean.CounterDetails;

class HomeServiceImplTest {
    private final ObjectMapper mapper = new ObjectMapper()
            .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);

    @Test void graphResponseContainsExactlyOneJsonDocumentAndSixtySamples() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
        new HomeServiceImpl().graph(new MockHttpServletRequest("GET", "/nyaan/graph"), response);
        JsonNode json = mapper.readTree(response.getContentAsString());
        assertEquals("application/json", response.getContentType());
        assertEquals(60, json.get("countArray").size());
    }

    @Test void graphSerializationReturnsJsonWithoutOwningTheResponseWriter() throws Exception {
        JsonNode json = mapper.readTree(new HomeServiceImpl().createGraphJson());
        assertTrue(json.get("countArray").isArray());
    }

    @Test void getDoesNotIncrementCountAndPostIncrementsOnce() throws Exception {
        HomeServiceImpl service = new HomeServiceImpl();
        long before = CounterDetails.getInstance().getCount();
        MockHttpServletResponse get = new MockHttpServletResponse();
        service.json(new MockHttpServletRequest("GET", "/nyaan/json"), get);
        assertEquals(before, mapper.readTree(get.getContentAsString()).get("count").asLong());
        MockHttpServletResponse post = new MockHttpServletResponse();
        service.json(new MockHttpServletRequest("POST", "/nyaan/json"), post);
        assertEquals(before + 1, mapper.readTree(post.getContentAsString()).get("count").asLong());
        CounterDetails.getInstance().setCount(before);
    }
}
