/*
 * Copyright (C) 2013 tarent AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.osiam.storage.entities

import org.osiam.resources.scim.Email;
import org.osiam.resources.type.EmailType;

import spock.lang.Specification;

/**
 * Created with IntelliJ IDEA.
 * User: jtodea
 * Date: 15.03.13
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
class EmailEntitySpec extends Specification {

    EmailEntity emailEntity = new EmailEntity()
    def userEntity = Mock(UserEntity)

    def "setter and getter for the email should be present"() {
        when:
        emailEntity.setValue("work@high.tech")

        then:
        emailEntity.getValue() == "work@high.tech"
    }

    def "setter and getter for the type should be present"() {
        when:
        emailEntity.setType(EmailType.HOME)

        then:
        emailEntity.getType() == EmailType.HOME
    }

    def "setter and getter for primary should be present"() {
        when:
        emailEntity.setPrimary(true)

        then:
        emailEntity.isPrimary()
    }

    def "setter and getter for user should be present"() {
        when:
        emailEntity.setUser(userEntity)

        then:
        emailEntity.getUser() == userEntity
    }

    def "mapping to scim should be present"() {
        when:
        def multivalue = emailEntity.toScim()

        then:
        multivalue.type == emailEntity.type
        multivalue.value == emailEntity.value
        multivalue.isPrimary() == emailEntity.primary
    }



    def "mapping from scim should be present"() {
        given:
        Email multiValuedAttribute = new Email.Builder().
                setPrimary(true).
                setType(EmailType.WORK).
                setValue("value").
                build()

        when:
        def result = EmailEntity.fromScim(multiValuedAttribute)

        then:
        result != null
    }

    def "mapping from scim should set primary to false when null"() {
        given:
        Email multiValuedAttribute = new Email.Builder().
                setPrimary(null).
                setType(EmailType.WORK).
                setValue("value").
                build()

        when:
        def result = EmailEntity.fromScim(multiValuedAttribute)

        then:
        !result.primary
    }

    def "setter and getter for id should be present"() {
        when:
        emailEntity.setMultiValueId(1234)

        then:
        emailEntity.getMultiValueId() == 1234
    }
}