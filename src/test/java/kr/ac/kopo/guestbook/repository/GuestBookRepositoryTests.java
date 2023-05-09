package kr.ac.kopo.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.ac.kopo.guestbook.entity.Guestbook;
import kr.ac.kopo.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;

    //Querydsl을 사용하여 특정검색어를 갖는 행들만 선택
    //단일 검색어 테스트
    @Test
    public void testQuery2(){
        Pageable pageable =PageRequest.of(0,10,Sort.by("gno").ascending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword= "7";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expTitle =qGuestbook.title.contains(keyword);
        BooleanExpression expWriter =qGuestbook.writer.contains(keyword);
        BooleanExpression expAll = expTitle.and(expWriter);
        builder.and(expAll);
        builder.and(qGuestbook.gno.gt(50L));
        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }


    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title...." + i)
                    .content("Content..." +i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest() {

        Optional<Guestbook> result = guestbookRepository.findById(300L); // Test with an existing id

        if(result.isPresent()){

            Guestbook guestbook = result.get();

            guestbook.changeTitle("Changed Title....");
            guestbook.changeContent("Changed Content...");

            guestbookRepository.save(guestbook);
        }
    }

}
