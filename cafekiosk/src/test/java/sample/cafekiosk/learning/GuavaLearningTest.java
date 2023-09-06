package sample.cafekiosk.learning;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GuavaLearningTest {

    @DisplayName("3개의 원소를 가지도록 List를 파티셔닝한다.")
    @Test
    void partitionLearningTest1() {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 3);

        // then
        assertThat(partition).hasSize(2)
                .isEqualTo(
                        List.of(
                                List.of(1, 2, 3), List.of(4, 5, 6)
                        )
                );
    }

    @DisplayName("4개의 원소를 가지도록 List를 파티셔닝한다.")
    @Test
    void partitionLearningTest2() {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 4);

        // then
        assertThat(partition).hasSize(2)
                .isEqualTo(
                        List.of(
                                List.of(1, 2, 3, 4), List.of(5, 6)
                        )
                );
    }

    @DisplayName("멀티맵의 기능을 확인한다.")
    @TestFactory
    Collection<DynamicTest> multimapLearningTest1() {
        // given
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("커피", "아메리카노");
        multimap.put("커피", "카푸치노");
        multimap.put("커피", "콜드브루");
        multimap.put("베이커리", "크루아상");
        multimap.put("베이커리", "식빵");

        return List.of(
            DynamicTest.dynamicTest("1개의 value를 삭제한다.", () -> {
                // when
                multimap.remove("커피", "콜드브루");

                // then
                Collection<String> strings = multimap.get("커피");
                assertThat(strings).hasSize(2)
                        .isEqualTo(List.of("아메리카노", "카푸치노"));
            }),
            DynamicTest.dynamicTest("1개의 key를 삭제하여 모든 value를 삭제한다.", () -> {
                // when
                multimap.removeAll("커피");

                // then
                Collection<String> strings = multimap.get("커피");
                assertThat(strings).isEmpty();
            })

        );
    }

}
