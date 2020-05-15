package com.westboy.netty.example08;

import com.westboy.thrift.generated.DataException;
import com.westboy.thrift.generated.Person;
import com.westboy.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @author westboy
 * @date 2018-12-15
 * @since 1.0
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUserName(String username) throws DataException, TException {
        System.out.println("Got Client Param: " + username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Save Person: " + person);

    }
}
