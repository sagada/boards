package com.example.demo;

import com.example.demo.domain.PDSBoard;
import com.example.demo.domain.PDSFile;
import com.example.demo.repository.PDSBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDBBoardTests {

    @Autowired
    private PDSBoardRepository pdsBoardRepository;

    @Test
    public void testInsertPDS()
    {
        PDSBoard pdsBoard = new PDSBoard();
        pdsBoard.setPanme("Document");

        PDSFile pdsFile1 = new PDSFile();
        pdsFile1.setPdsfile("file1.doc");

        PDSFile pdsFile2 = new PDSFile();
        pdsFile2.setPdsfile("file2.doc");

        pdsBoard.setPdsFileList(Arrays.asList(pdsFile1, pdsFile2));

        log.info("try to save pds");

        pdsBoardRepository.save(pdsBoard);
    }

    @Test
    @Transactional
    public void PDSFile_updateTEST()
    {
        Long fno = 1L;
        String newName = "updateNewName";

        int count = pdsBoardRepository.updatePDSFile(fno, newName);
        log.info("update Count : " + count);
    }

    @Test
    @Transactional
    public void PDSFIle_updateTest2()
    {
        String fileName = "updateFile.doc";

        Optional<PDSBoard> result = pdsBoardRepository.findById(2L);

        result.ifPresent(pds->{
            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(fileName);

            int idx = pds.getPdsFileList().indexOf(target);

            if (idx > -1)
            {
                List<PDSFile> list = pds.getPdsFileList();
                list.remove(idx);
                list.add(target);
                pds.setPdsFileList(list);
            }

            pdsBoardRepository.save(pds);
        });
    }

    @Test
    public void insertDummies(){
        List<PDSBoard> list = new ArrayList<>();

        IntStream.range(1, 100).forEach(i->{
            PDSBoard pds = new PDSBoard();

            pds.setPanme("자료" + i);

            PDSFile file1 = new PDSFile();
            file1.setPdsfile("file1.doc");

            PDSFile file2 = new PDSFile();
            file2.setPdsfile("file2.doc");


            pds.setPdsFileList(Arrays.asList(file1, file2));

            log.info("try to save pds");

            list.add(pds);
        });

        pdsBoardRepository.saveAll(list);

    }
}
