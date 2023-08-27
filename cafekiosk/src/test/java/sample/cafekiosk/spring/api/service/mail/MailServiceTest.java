package sample.cafekiosk.spring.api.service.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

// 순수 Mock을 활용한 단위 테스트
@ExtendWith(MockitoExtension.class)
class MailServiceTest {

//    @Mock
    @Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일이 전송된다.")
    @Test
    void sendMail() {
        // given

        // @Mock, @InjectMock 어노테이션을 통해 주입 가능
//        MailSendClient mailSendClient = mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        // Stubbing
//        when(mailSendClient.sendEmail(anyString(),anyString(), anyString(), anyString()))
//                .thenReturn(true);

        // @Spy 사용시에는 doReturn을 사용한다.
        doReturn(true)
                .when(mailSendClient)
                .sendEmail(anyString(), anyString(), anyString(), anyString());

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();

        // mailSendHistoryRepository가 1번 호출되었을 때, save 메서드를 호출했을 때 MailSendHistory 객체를 인자로 받는지를 검증하다.
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }

}