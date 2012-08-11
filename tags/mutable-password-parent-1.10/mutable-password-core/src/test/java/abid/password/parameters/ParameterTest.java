/**
 * Copyright 2012 Abid Khalil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package abid.password.parameters;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ParameterTest {

  @Test
  public void getValueShouldReturnParameterValue() {
    Parameter parameter = new Parameter("someValue");
    assertThat(parameter.getValue()).isEqualTo("someValue");

    parameter = new Parameter(new Integer(99));
    assertThat(parameter.getValue()).isEqualTo(99);
  }
}
