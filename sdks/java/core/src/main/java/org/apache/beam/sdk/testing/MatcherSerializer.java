/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.beam.sdk.testing;

import org.apache.beam.sdk.util.SerializableUtils;

import com.google.api.client.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * MatcherSerializer is used with Jackson to enable serialization of SerializableMatchers.
 */
class MatcherSerializer extends JsonSerializer<SerializableMatcher<?>> {
  @Override
  public void serialize(SerializableMatcher<?> matcher, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
    byte[] out = SerializableUtils.serializeToByteArray(matcher);
    String encodedString = Base64.encodeBase64String(out);
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("matcher", encodedString);
    jsonGenerator.writeEndObject();
  }
}