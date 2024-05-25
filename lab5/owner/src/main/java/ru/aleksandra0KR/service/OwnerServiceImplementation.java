package ru.aleksandra0KR.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.aleksandra0KR.dao.Owner;
import ru.aleksandra0KR.dto.OwnerDtoClient;
import ru.aleksandra0KR.exception.PersonDoesntExistException;
import ru.aleksandra0KR.repository.OwnerRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandra0KR.ru.dto.OwnerDtoMessage;


@Component
@EnableRabbit
public class OwnerServiceImplementation implements OwnerService {

  final OwnerRepository ownerRepository;

  public OwnerServiceImplementation(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Override
  public OwnerDtoClient findPersonByID(long id) {
    Owner owner = ownerRepository.findById(id)
        .orElseThrow(() -> new PersonDoesntExistException(id));
    return OwnerDtoClient.builder()
        .owner_id(owner.getOwner_id())
        .birthday(owner.getBirthday())
        .name(owner.getName())
        .person_id(owner.getPersonId())
            .build();
  }

  @Override
  public OwnerDtoClient findPersonByName(String name) {
    var owner = ownerRepository.findByName(name);
    System.out.println(owner);
    if (owner == null) {
      throw new PersonDoesntExistException(name);
    }
    return OwnerDtoClient.builder()
        .owner_id(owner.getOwner_id())
        .birthday(owner.getBirthday())
        .name(owner.getName())
        .person_id(owner.getPersonId())
        .build();
  }

  @RabbitListener(queues = "ownerUpdateQueue")
  @Transactional
  @Override
  public void updatePerson(OwnerDtoMessage person) {

    Owner owner = ownerRepository.findById(person.getId())
        .orElseThrow(() -> new PersonDoesntExistException(person.getId()));

    owner.setName(person.getName());
    owner.setBirthday(person.getBirthDay());
    ownerRepository.save(owner);
  }

  @RabbitListener(queues = "ownerDeleteQueue")
  @Transactional
  @Override
  public void deletePerson(Long id) {
    Owner owner = ownerRepository.findById(id)
        .orElseThrow(() -> new PersonDoesntExistException(id));
    ownerRepository.delete(owner);
  }



  @RabbitListener(queues = "ownerAddQueue")
  @Transactional
  @Override
  public void addOwner(OwnerDtoMessage ownerDtoMessage) {


    Owner dao = ownerRepository.save(
        Owner.builder()
            .name(ownerDtoMessage.getName())
            .owner_id(ownerDtoMessage.getId())
            .birthday(ownerDtoMessage.getBirthDay())
            .personId(ownerDtoMessage.getPerson_id())
            .build()
    );
    Long generatedId = dao.getOwner_id();
    ownerDtoMessage.setId(generatedId);
  }
}