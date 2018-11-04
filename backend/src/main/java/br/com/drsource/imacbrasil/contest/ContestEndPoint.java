package br.com.drsource.imacbrasil.contest;

import br.com.drsource.imacbrasil.exception.ImacException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("contest")
public class ContestEndPoint {


    @Autowired
    private ContestService contestService;

    @GetMapping
    @PreAuthorize("hasRole('CONTEST.CAN_LIST')")
    public ResponseEntity<List<Contest>> listAll(){
        return ResponseEntity.ok(contestService.listAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('CONTEST.CAN_SAVE')")
    public ResponseEntity<Void> save(@RequestBody Contest contest) throws ImacException {
        contestService.save(contest);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/contest/{id}")
                    .buildAndExpand(contest.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CONTEST.CAN_LIST')")
    public ResponseEntity<Contest> findById(@PathVariable Short id){
        return contestService
                .findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound()
                        .build());
    }
}
