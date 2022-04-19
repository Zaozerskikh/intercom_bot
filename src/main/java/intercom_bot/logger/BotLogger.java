package intercom_bot.logger;

import intercom_bot.data.entities.Intercom;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Arrays;
import java.util.List;

@Log
@Aspect
@Configuration
public class BotLogger {
    @Before("execution(* handle(..))")
    public void logUpdate(JoinPoint point) {
        if (((Update)point.getArgs()[1]).hasMessage() && ((Update)point.getArgs()[1]).getMessage().hasText()) {
            log.info("[REQUEST  ]: chatID = \"" + ((Update)point.getArgs()[1]).getMessage().getChatId() +
                    "\" [@" + ((Update)point.getArgs()[1]).getMessage().getChat().getUserName() +
                    "] --> \"" + ((Update)point.getArgs()[1]).getMessage().getText() + "\"");
        }
    }

    @AfterReturning(pointcut = "execution(* parseMessage(..))", returning = "request")
    public void logParse(JoinPoint point, String[] request) {
        log.info("[PARSE  OK]: chatID = \"" + ((Update)point.getArgs()[0]).getMessage().getChatId() +
                "\" [@" + ((Update)point.getArgs()[0]).getMessage().getChat().getUserName() +
                "], request = \"" + Arrays.toString(request) + "\"");
    }

    @AfterThrowing(pointcut = "execution(* parseMessage(..))", throwing = "parse_error")
    public void logParseError(JoinPoint point, RuntimeException parse_error) {
        log.info("[PARSE ERR]: chatID = \"" + ((Update)point.getArgs()[0]).getMessage().getChatId() +
                "\" [@" + ((Update)point.getArgs()[0]).getMessage().getChat().getUserName() +
                "], request = \"" + parse_error.getMessage() + "\"");
    }

    @AfterReturning("execution(* sendText(..))")
    public void logTextResponse(JoinPoint point) {
        log.info("[RESPONSE ]: chatID = \"" + point.getArgs()[1] + "\" msg = \"" + point.getArgs()[2] + "\"");
    }

    @AfterReturning("execution(* sendList(..))")
    public void logListResponse(JoinPoint point) {
        log.info("[RESPONSE ]: chatID = \"" + point.getArgs()[1] + "\" msg = \"" + point.getArgs()[2] + "\"");
    }

    @AfterReturning(pointcut = "execution(* findAddress(..))", returning = "intercoms")
    public void logFindAddress(JoinPoint point, List<Intercom> intercoms) {
        log.info("[FINDED   ]: request = \"" + Arrays.toString((String[])point.getArgs()[0]) +
                "\", result_count = \"" + intercoms.size() + "\"");
    }

    @AfterReturning("execution(* initIntercomData(..))")
    public void logDataInit(JoinPoint point) {
        log.info("[DATA INIT] --> COMPLETED");
    }
}
