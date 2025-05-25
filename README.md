## 대중교통 자동 결제 서비스 PayPass
### 교통 약자를 위한 대중교통 자동 결제 서비스'PayPass'


<img src="https://github.com/user-attachments/assets/00c45dad-44e7-46b6-806e-7fcc27c62b51" style="display: block; margin: 0 auto; width: 60%;">

<hr>

### 설명
대중교통 이용 시 카드 태그 없이 자동 결제가 이뤄집니다.

교통 약자(노약자, 신체장애인)의 편의와 사고를 방지하기 위해 서비스를 구현했습니다.

BLE 대신 GPS 데이터를 활용했기에 핸드폰이 어디에 있어도 작동합니다.


<hr>

### 주요 기능
#### 구글 로그인 연동
* Oauth2를 사용하여 구글 아이디로 서비스를 이용할 수 있습니다.

#### 사용자 대중교통 승차 및 하차 시 자동 결제
* 사용자의 위치 데이터를 기반으로 하차 30분 이후에 자동으로 탑승 여부를 판별합니다.

#### 실시간 이용 내역 확인 및 관리
* 현재까지 사용했던 내역을 확인할 수 있으며 에러 발생 시 즉시 문의를 보낼 수 있습니다.

#### 교통 약자를 위한 보호자 결제 서비스
* 보호자의 연락처를 입력하면 대신 결제를 진행할 수 있습니.

<hr>

### 시스템 아키텍처 
<img src="https://github.com/user-attachments/assets/8891d89e-3342-4c0a-89cb-a04cc10f6daf" style="display: block; margin: 0 auto; width: 60%;">

* Backend: Java & Spring boot
* Frontend: Dart & Flutter
* Server: AWS EC2
* DataBase: MySQL

<hr>

### 추가 예정 사항
* 보호자 로그인
* 사용자 거주지 및 보호시설 진입 시 알림 서비스
