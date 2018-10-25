package br.com.drsource.imacbrasil.contest;

import br.com.drsource.imacbrasil.exception.ImacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContestService {

    @Autowired
    private ContestRepository contestRepository;

    public void save(Contest contest) throws ImacException {
//        if (contest.getEndDate()..before(contest.getBeginDate())){
//            throw new ImacException("The end date must be before the begin date");
//        }

        contestRepository.save(contest);

    }

    public List<Contest> listAll(){
        List<Contest> contests = new ArrayList<>();
        contestRepository.findAll().forEach(contests::add);
        return contests;
    }

    public void delete(Contest contest){
        contestRepository.delete(contest);
    }

    public Optional<Contest> findById(Short id){
        return contestRepository.findById(id);
    }


}
