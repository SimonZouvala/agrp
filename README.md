# ONEPOST AI Assistant (Groq Integration)

### 🚀 Mini Hackathon – Proof of Concept
Tento projekt demonstruje, jak lze do systému **ONEPOST** integrovat umělou inteligenci (AI) pro generování denních přehledů, analýzu zpráv a predikci úkolů.

Projekt využívá:
- **Java 20 (Amazon Corretto)**
- **Spring Boot**
- **Groq API (kompatibilní s OpenAI API)**
- **OkHttp + Jackson** pro komunikaci s API

---

## Záměr

Záměrem je vytvořit AI asistenta, který:
- analyzuje počet nepřečtených nebo nevyřízených zpráv,
- vytvoří stručný „denní přehled“,
- doporučí, **co udělat dnes, aby zítra byl klid** 🧘‍♂️.

Např.:
> „Máš 14 nepřečtených zpráv, z toho 3 od úřadů. Doporučuji dnes odpovědět na všechny zprávy z finančního úřadu, aby zítra bylo klidněji.“


---

## 💬 Příklad komunikace

**HTTP Request (Postman / CURL):**

```
POST http://localhost:8080/api/summary
Content-Type: application/json

{
  "messages": [
    {
      "subject": "Výzva k podání vysvětlení",
      "read": false,
      "deadline": "2025-10-27"
    },
    {
      "subject": "Žádost o doplnění údajů",
      "read": false,
      "deadline": "2025-10-28"
    }
  ]
}
```

**Response (z Groq AI):**
```json
{
  "id": "chatcmpl-fb95a0d3-afcc-40a6-a360-240f52ee1b1b",
  "object": "chat.completion",
  "created": 1762102554,
  "model": "llama-3.3-70b-versatile",
  "choices": [
    {
      "index": 0,
      "message": {
        "role": "assistant",
        "content": "\"Holly, tady hlásím! Souhrn zpráv za poslední dobu: máme dvě nepřečtené zprávy, obě vyžadují akci. První je výzva k podání vysvětlení, která musí být vyřízena do 27. října, a druhá je žádost o doplnění údajů, jejíž termín je 28. října. Doporučení na dnešní den: raději se pustíme do těch vysvětlení a doplnění údajů, abychom měli zítřek klidný. Až bude po všem, můžeme si konečně dát onu slavnou kávu, kterou si už dávno zasloužíme.\n\nA mimochodem, Listera už týden nenachází svou oblíbenou čajovou lžičku. Kdo ví, zda ji nezahodil Rimmer při své poslední 'úklidové' akci?\""
      },
      "logprobs": null,
      "finish_reason": "stop"
    }
  ],
  "usage": {
    "queue_time": 0.085974722,
    "prompt_tokens": 241,
    "prompt_time": 0.021409006,
    "completion_tokens": 190,
    "completion_time": 0.880089604,
    "total_tokens": 431,
    "total_time": 0.90149861
  },
  "usage_breakdown": null,
  "system_fingerprint": "fp_2ddfbb0da0",
  "x_groq": {
    "id": "req_01k92qyb0mfhdsc1btcjg11hy1"
  },
  "service_tier": "on_demand"
}
```

---

## 🧩 Hlavní komponenty

| Soubor | Popis                                                |
|--------|------------------------------------------------------|
| `OpenAiClient.java` | Jednoduchý HTTP klient pro komunikaci s Groq API     |
| `OpenPostService.java` | Logika aplikace – sestavuje AI prompt a volá klienta |
| `OnePostApi.java` | REST controller pro přístup přes HTTP                |
| `pom.xml` | Maven konfigurace pro JDK 20 a Spring Boot           |
| `application.yml` | Konfigurace (API klíče, model, url apod.)            |

---

## ⚙️ Spuštění lokálně

### 1️⃣ Nastav Groq API klíč
Zaregistruj se zdarma na [https://console.groq.com](https://console.groq.com)  
a získej API klíč.

V application.yml je možné klíč nastavit přímo
```yml
openai:
  api:
    key: "tvuj_api_klic"
```

Eventuelně se dá nastavit i Mock volání. Pro tento případ je nutné v application.yml nastavit:
```yml
openai:
  api:
    ...
    mock: true
```

Ještě je možné nastavi jaký model se má použít viz [https://console.groq.com/docs/models](https://console.groq.com/docs/models):
```yml
openai:
  api:
    ...
    model: "llama-3.3-70b-versatile"
```
---

### 2️⃣ Spusť aplikaci
```bash
mvn spring-boot:run
```

---

### 3️⃣ Otestuj přes Postman / cURL
```bash
curl -X POST http://localhost:8080/api/summary   -H "Content-Type: application/json"   -d '{
  "messages": [
    {
      "subject": "Výzva k podání vysvětlení",
      "read": false,
      "deadline": "2025-10-27"
    },
    {
      "subject": "Žádost o doplnění údajů",
      "read": false,
      "deadline": "2025-10-28"
    }
  ]
}'
```

---


## Použité technologie

| Technologie | Verze                |
|--------------|----------------------|
| Java | 20 (Amazon Corretto) |
| Spring Boot | 3.3.0                |
| Maven | 4.0.0                |
| OkHttp | 3.14.9               |
| Jackson | 2.9.0                |
| Lombok | 1.18.28              |

---


## Autor

**Šimon Zouvala**  
Java Developer   
[LinkedIn profil](https://cz.linkedin.com/in/%C5%A1imon-zouvala-0074051b0)

