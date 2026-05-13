# bitmapDB
測試RoaringBitmap在mongodb和redis儲存和使用，那麼業務是以優惠券的領/用/查詢等動作做示範

# 程式啓動

## IDE 開發（IntelliJ）
VM options:
```
-Dspring.config.location=C:/Data/code/bitmapDB/src/main/resources/config/ -Dconfig.base=./src/main/resources/config
```
Program arguments:
```
--spring.profiles.active=uat --app.redis.mode=single
```

## JAR 部署
```bash
java -Dconfig.base=./config -jar bitmapDB.jar --spring.profiles.active=uat --app.redis.mode=single
```
部署目錄結構：
```
deploy/
├── bitmapDB.jar
└── config/
    ├── application-uat.properties
    ├── datasource/
    │   └── mongo-uat.properties
    └── middleware/
        └── redis-uat.properties
```

## redis single
```
--app.redis.mode=single
```

## redis cluster
```
--app.redis.mode=cluster
```

---

## 配置檔案說明

配置檔案在 `src/main/resources/config/` 裡面：
- `application-uat.properties` — 主配置，含 `spring.config.import` 指向子配置檔
- `datasource/mongo-uat.properties` — MongoDB 連線設定
- `middleware/redis-uat.properties` — Redis 連線設定

### 載入機制
`application-uat.properties` 中的 `spring.config.import` 使用**相對路徑**：
```properties
spring.config.import=\
  optional:file:./datasource/mongo-uat.properties,\
  optional:file:./middleware/redis-uat.properties
```
`file:./` 會以 `spring.config.location` 指定的目錄為基準解析：
- IDE：`-Dspring.config.location` 指向 `src/main/resources/config/` → 正確找到子配置
- JAR 部署：Spring Boot 預設搜尋 `file:./config/` → 正確找到子配置

### 新增環境配置
1. 複製 `application-uat.properties` → `application-prod.properties`，修改 import 路徑指向 `mongo-prod.properties`、`redis-prod.properties`
2. 在 `datasource/`、`middleware/` 下新增對應的 `-prod.properties`

### 命名慣例
子配置檔使用**自定義名稱**（如 `mongo-uat.properties`），不使用 `application-xxx.properties` 格式，以清楚區分用途。

### 打包 JAR（排除 config）
```bash
mvn clean package -Ppackage -DskipTests
```
使用 `-Ppackage` 會排除 `config/**`，部署時將 `config/` 目錄放在 JAR 旁邊即可。
