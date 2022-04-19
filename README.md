# Intercom Bot
Telegram-бот, обеспечивающий удобное взаимодействие <br> с базой данных кодов московских домофонов.

Паттерн запросов к боту:<br>

- <*Название улицы*> <*Номер дома*> <*?Номер корпуса*>; <*Номер подъезда*> <br>

Рекомендации по использованию:
- Добавлять в запрос ключевые слова "ул.", "улица", "проспект", "д." и т. д. **не требуется**.<br>
Их включение в запрос может негативно сказаться на результатах поиска.
- Номер корпуса является необязательной опцией.
- Последняя цифра запроса (номер подъезда) обязательно отделяется двоеточием.

Примеры запросов:
