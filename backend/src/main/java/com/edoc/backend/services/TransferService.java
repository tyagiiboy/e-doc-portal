package com.edoc.backend.services;

import com.edoc.backend.dto.TransferDto;
import com.edoc.backend.dto.UserDto;
import com.edoc.backend.entities.Admission;
import com.edoc.backend.entities.School;
import com.edoc.backend.entities.Transfer;
import com.edoc.backend.entities.User;
import com.edoc.backend.repositories.SchoolRepository;
import com.edoc.backend.repositories.TransferRepository;
import com.edoc.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransferService {

  @Autowired
  private TransferRepository transferRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SchoolRepository schoolRepository;

  @Autowired
  private ModelMapper mapper;

  @Modifying
  public Transfer createTransfer(long userId, long diseCode) {
    User user = userRepository.findById(userId).orElseThrow();
    School school = schoolRepository.findById(diseCode).orElseThrow();
    Transfer transfer = Transfer.builder()
        .user(user)
        .respondant(school)
        .build();
    return transferRepository.save(transfer);
  }

  public List<TransferDto> getAllByDiseCode(long diseCode) {
    List<Transfer> transfers = transferRepository.findBySchoolDiseCode(diseCode);

    if (transfers == null) return null;

    List<TransferDto> list = new ArrayList<>();
    transfers.forEach(
        transfer -> list.add(
            TransferDto.builder()
                .id(transfer.getId())
                .user(mapper.map(transfer.getUser(), UserDto.class))
                .build()
        )
    );

    return list;
  }

  @Modifying
  public void acceptListedTransfersBySchool(List<Long> transfers, long diseCode) {
    School school = schoolRepository.findById(diseCode).orElseThrow();
    List<Transfer> transferList = transferRepository.findAllById(transfers);

    transferList.forEach(
        transfer -> {
          transfer.getUser().setSchool(school);
          school.getUserList().add(transfer.getUser());
          school.getAdmissionList().add(
              Admission.builder()
                  .user(transfer.getUser())
                  .schoolClass(transfer.getSchoolClass())
                  .school(school)
                  .stream(transfer.getStream())
                  .build()
          );
        }
    );
    schoolRepository.save(school);
    rejectListedTransfersBySchool(transfers, diseCode);
  }

  public void rejectListedTransfersBySchool(List<Long> transfers, long diseCode) {
    transferRepository.deleteAllById(transfers);
  }

}
