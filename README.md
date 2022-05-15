<div align="center">
    <br/>
    <h1><strong><em>🌳 Walkerholic</em></strong></h1>
    <img width="800" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139655644-a785c4b3-e735-447c-bbb6-2e9743cb3b35.jpeg">
    <br/>
    <br/>
    <p>
      Walkerholic은 <strong><em>자연을 위한 활동</em></strong> 을 목표로 만들어진 앱입니다.<br/> 
      최근 급격한 기후변화 등 세계 위협 요인으로 뽑혀지는 환경문제에 '작은 활동이라도 기여하고 지구와 함께 나아가자'라는 주제로 만들어졌습니다.<br/>
      환경과 관련된 활동(플로깅, 일회용품 사용하지 않기, 대중교통 사용하기 등)을 기록하고 레벨을 높일 수 있습니다.<br/>
      자연 보호에 관한 게시물을 올리고 다른 사람의 최근 게시물을 확인하고 팔로우할 수 있습니다.<br/>
      친환경적인 제품을 만드는 판매자들은 상품을 등록하고 회원들에게 판매할 수 있습니다. <br/>
    </p>
    <br/>
    <br/>
    <a href="https://walkerholic-with-you.herokuapp.com/"><strong>walkerholic-with-you</strong></a>
</div>
    <br/>
    <br/>
    <br/>
<div>
    <h2>🚀 Shortcut</h2>
<div> 
        
- [__Improvement__](#improvement)
- [__Challenges__](#challenges)
- [__Tech Stack Used__](#tech)
- [__Implementation__](#implementation)
- [__Features__](#feature)
- [__Entity Relationship Diagram__](#erd)
- [__Structure__](#structure)

</div>
    <br/>
    <br/>
    <br/>
<div>
    <h2 id="improvement">🍑 Improvement</h2>
    <ul>
      <li>
        <h3>✔️ OAuth2</h3> 
          <ul>
            <li>
              <h4>Challenge : </h4>
              <p>사용자의 편리성을 위하여 구글, 네이버, 카카오의 소셜 로그인 기능을 만들고자 하였습니다. </p>
            </li>
            <li>
              <h4>Solution : </h4>
              <p>
                Spring Security OAuth를 통하여 Rest API를 이용한 소셜 로그인 기능이 가능하도록 구현하였습니다. 
                WebSecurityConfig에서 OauthLogin의 URI설정을 해주고 설정된 주소로 OAuth2UserRequest타입의 인증 요청이 들어오면 CustomOAuth2UserService에서 소셜타입별로 설정해놓은 OAuth2UserInfo를 통해 정보를 받아온 뒤, 회원 등록 혹은 업데이트가 가능하도록 설정하였습니다.
                인증에 성공한다면 OAuth2SuccessHandler를 통해 등록된 회원의 토큰을 HttpCookieOAuth2AuthorizationRequestRepository를 이용해 들어온 주소와 토큰을 함께 되돌려 주도록 하고, 실패한다면 OAUth2AuthenticationFailureHandler를 통해 error 요인과 함께 이전 주소로 되돌려 보내주었습니다. 
              </p>
            </li>
          </ul>       
      </li>
      <li>
        <h3>✔️ SpringData JPA</h3> 
          <ul>
            <li>
              <h4>Challenge : </h4>
              <p>
                기존에는 Crud repository를 이용하여 데이터 처리를 구현했지만, 좀 더 편리하게 인터페이스 작성만으로도 데이터를 가져오도록 처리하고 싶었습니다. 
              </p>
            </li>
            <li>
              <h4>Solution : </h4>
              <p>
                SpringData JPA는 개발시 인터페이스만 작성하면 실행 시점에 구현 객체를 동적으로 주입해주기 때문에(런타임 에러 방지), 이를 이용하여 등록된 column명을 이용한 인터페이스 작성만으로도 쿼리메서드를 통해 데이터를 가져오도록 편리하게 repository계층을 작성할 수 있었습니다. 
              </p>
            </li>
          </ul>
      </li>
      <li>
        <h3>✔️ Amazon S3 </h3> 
          <ul>
            <li>
              <h4>Challenge : </h4>
              <p>
                사용자가 등록하려는 이미지를 MultipartFile타입으로 서버측에서 받아 파일을 보관할 때, 업로드 양이 증가함에 따라 업로드 폴더와 같은 로컬 저장소가 아닌 원격으로 이미지를 업로드하는 것이 요구되었습니다. 
              </p>
            </li>
            <li>
              <h4>Solution : </h4>
              <p>
                AWS S3를 이용하여 버킷을 생성하고 AmazonS3Utils을 만들어 amazonS3Client를 이용한 원격으로의 파일업로드, 파일 삭제, 폴더 삭제 등의 기능을 구현하였습니다.  
              </p>
            </li>
          </ul>
      </li>
      <li>
        <h3>✔️ JavaMailSender (Simple Mail Message)</h3> 
          <ul>
            <li>
              <h4>Challenge : </h4>
              <p>
                구매자가 주문을 완료한 뒤, 구매자에게 이메일을 발송하도록 서버측에서 메일발송이 이루어지도록 하고자 하였습니다. 
              </p>
            </li>
            <li>
              <h4>Solution : </h4>
              <p>
                smtp설정을 네이버 메일 host를 이용하여 JavaMailSender(spring-boot-starter-mail)을 이용해 SimpleMailMessage타입의 메일이 전송되도록 설정하였습니다. 
              </p>
            </li>
          </ul>
      </li>
    </ul>
</div>
    <br/>
    <br/>
    <br/>
<div>
    <h2 id="challenges">⚡️ Challenges</h2>
    <ul>
      <li>
        <h3>✔️ JPA delete 쿼리 실행 문제 </h3> 
          <ul>
            <li>
              <h4>Reason : </h4>
              <p>
                프로젝트 진행시에 게시물 이미지 엔티티를 삭제하기 위하여 JPA repository를 이용한 인터페이스 명을 작성하였으나 실행이 되지 않았습니다. 이는 @Transactional 어노테이션이 붙어있는 JPA Repository 목록 안에 deleteBy 형태가 존재하지 않기 때문이었습니다. (EntityManager opne -> SELECT 쿼리 실행 -> EntityManager closed -> DELETE 쿼리 예외 발생 순으로 진행되었습니다.)
              </p>
            </li>
            <li>
              <h4>Solution : </h4>
              <p>
                해당 문제를 해결하기 위해서 사용하고자 하는 deleteBy인터페이스 명에 @Transactional 어노테이션을 추가하여 문제를 해결하였습니다. 
              </p>
            </li>
          </ul>
      </li>
      <li>
        <h3>✔️ 순환 참조를 방지하는 DTO 작성 문제</h3> 
          <ul>
            <li>
              <h4>Reason : </h4>
              <p>
                연관관계의 데이터를 DTO를 통해서 보내고자 할 때, 연관관계의 데이터를 그대로 보내려고 하면 양방향 연관관계의 데이터가 계속해서 서로를 부르며 순환 참조가 발생하는 문제가 발생하게 되었습니다.    
              </p>
            </li>
            <li>
              <h4>Solution : </h4>
              <p>
                해당문제를 해결하기 위해서 해당 DTO안에 생성자를 가진 새로운 클래스를 만들어준 후 연관관계의 데이터 정보 중 보내야하는 선택부분만(연관관계부분 제외) 생성자를 이용해 넣도록 설정하여 참조된 데이터를 보낼 수 있도록 하였습니다. 
              </p>
            </li>
          </ul>
      </li>
     </ul>
</div>
    <br/>
    <br/>
    <br/><div>
    <h2 id="tech">🛠 Tech Stack Used</h2>
    <ul>
      <li>
        <h4>Frontend</h4> 
        <img src="https://img.shields.io/badge/react-6cc1d9?style=for-the-badge&logo=react&logoColor=white">
        <img src="https://img.shields.io/badge/redux-bb93e6?style=for-the-badge&logo=redux&logoColor=white">
        <img src="https://img.shields.io/badge/fontawesome-26324f?style=for-the-badge&logo=fontawesome&logoColor=white">
        <img src="https://img.shields.io/badge/bootstrap-574370?style=for-the-badge&logo=bootstrap&logoColor=white">
      </li>
      <li>
        <h4>Backend</h4> 
        <img src="https://img.shields.io/badge/springboot-67a155?style=for-the-badge&logo=springboot&logoColor=white">
        <img src="https://img.shields.io/badge/springsecurity-6aa667?style=for-the-badge&logo=springsecurity&logoColor=white">
        <br/>
        <img src="https://img.shields.io/badge/springsecurity-oauth2-6aa667?style=for-the-badge&logo=springsecurity&logoColor=white">
        <img src="https://img.shields.io/badge/springboot-test-6aa667?style=for-the-badge&logo=springboot&logoColor=white">
        <img src="https://img.shields.io/badge/springboot-mail-6aa667?style=for-the-badge&logo=springboot&logoColor=white">
        <img src="https://img.shields.io/badge/springData-JPA-6aa667?style=for-the-badge&logo=springboot&logoColor=white">
        <img src="https://img.shields.io/badge/lombok-d15232?style=for-the-badge&logo=springboot&logoColor=white">
        <br/>
        <img src="https://img.shields.io/badge/mysql-e3ba68?style=for-the-badge&logo=mysql&logoColor=black">
        <img src="https://img.shields.io/badge/jwt-2b2b2b?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
      </li>
      <li>
        <h4>Deploy</h4> 
        <img src="https://img.shields.io/badge/heroku-ad7dac?style=for-the-badge&logo=heroku&logoColor=white">
      </li>
    </ul>
</div>
    <br/>
    <br/>
    <br/>
<div>
    <h2 id="implementation">🫐 Implementation</h2>
    <ul>
      <li>
        <h3>✔️ SpringBoot, React, MySQL & MVC pattern</h3> 
        <p>
          프로젝트는 StpringBoot(자바기반의 프레임워크), React(자바스크립트기반의 라이브러리), MySQL(관계형 데이터베이스)을 이용하여 구현되어졌으며 react를 이용한 프론트엔드를 만들고 SpringBoot를 이용한 서버 구축 후 MySQL 데이터베이스와 소통하여 적절한 정보를 클라이언트에게 보이도록 설정하였습니다. 
        </p>
      </li>    
      <li>
        <h3>✔️ MVC pattern</h3> 
        <p>
          Model, View, Controller 의 패턴을 통해 사용자 인터페이스로부터 로직을 분리하여 코드의 유지보수에 용이하도록 설정하였습니다.  
        </p>
      </li>        
      <li>
        <h3>✔️ Authenticaton & Authorization (JWT & Spring Security)</h3> 
        <p>
          사용자 인증 방법으로는 별도의 저장소를 필요하지 않는 JWT토큰을 이용하여 인증, 발급의 과정을 거쳐 이루어지도록 설정하였으며, 인증된 사용자의 권한 부여(인가)는 프론트엔드는 Custom Router, 백엔드는 Spring Security(springboot-starter-security)를 이용해 사용자의 역할(ROLE)에 따라서 데이터 접근 가능여부를 다르게 설정하였습니다. 
        </p>
      </li>
      <li>
        <h3>✔️ TDD (Test Driven Development)</h3> 
        <p>
          FIRST(Fast, Independent, Repeatable, Self Validating, Timely), given-when-then 규칙에 부합하도록 Repository계층과 Service계층의 단위테스트를 통해 안정적인 앱 개발을 진행하고자 하였습니다. 
        </p>
      </li>       
      <li>
        <h3>✔️ Pagination & Sort</h3> 
        <p>
          한번에 방대한 양의 데이터를 한번에 요구하는 것(서버 부하)을 방지하기 위해서 repository에서 countQuery를 이용하여 pagination기능을 구현하였습니다.
          Sort기능은 @Query 어노테이션에서 ORDER BY를 사용하거나 Pageable을 생성시에 Sort.by기능을 사용하여 기준을 정해 정렬된 데이터를 순서대로 가져오도록 설정헤 원하는 순서대로 데이터를 확인할 수 있도록 하였습니다.
        </p>
      </li>     
      <li>
        <h3>✔️ Heroku Deployment </h3> 
        <p>
           frontend-maven-plugins를 이용하여 스프링부트와 리액트를 동시에 실행시키는 .jar파일을 생성한 뒤 헤로쿠를 통한 배포를 구현하였습니다. 
        </p>
      </li>           
      <li>
        <h3>✔️ Infinite Scrolling</h3> 
        <p>
          Pagination기능과 함께 게시물 목록 부분(PostThumb)에서는 Intersection Observer와 useRef를 이용한 무한스크롤 기능을 구현하였습니다. 일정 부분이 화면에 나올 때마다 새로운 데이터를 받아오도록 설정하였습니다. 
        </p>
      </li>       
      <li>
        <h3>✔️ Redux</h3> 
        <p>리덕스를 통하여 View에서는 action만을 실행시키면 이 액션은 dispatcher를 통해서만 데이터변경이 가능하도록 설정해주었습니다. 변경된 데이터는 store를 통해서 View로 전달이 되도록 하여 단방향의 데이터 흐름이 이루어지도록 설정하였습니다. 또한 Constant를 지정하여 각각의 액션에 요청, 성공, 실패의 경우를 알아보기 쉽게 정의하였고 각각의 액션은 모두 저장되어 어떻게 데이터가 변경되었는지 확인할 수 있었습니다. </p>
      </li>
      <li>
        <h3>✔️ Payment</h3> 
        <p>소비자가 물품구매가 가능하도록 하기 위해 'react-paypal-button-v2'의 Paypal버튼을 이용하여 페이팔을 통한 실제 구매가 가능하도록 설정하였습니다. </p>
      </li>
      <li>
        <h3>✔️ Masonary Layout</h3> 
        <p>기존의 규격화 되어있는 그리드 형식이 아닌 벽돌실 레이아웃 타입으로 게시물 목록(PostThumb)를 구현하였습니다. </p>
      </li>
    </ul>
</div>
    <br/>
    <br/>
    <br/>
<div id="feature">
</div>
    
🪵 Features
--
<h4> 🥕 사용자, 게시물, 상품, 액티비티에 대한 CRUD(Create, Read, Update, Delete) 가능</h4>
<h4> 🥕 사용자의 액티비티, 게시물, 상품 등록 가능(연관관계)</h4>
<h4> 🥕 상품 등록 날짜, 리뷰순, 금액순으로 검색 가능</h4>
<h4> 🥕 게시물 등록 날짜, 리뷰순, 금액순으로 검색 가능</h4>
<h4> 🥕 Json Web Token을 이용한 인증/인가 기능</h4>
<h4> 🥕 JavaMailSender를 이용한 주문내역 확인 이메일 수신 가능</h4>
<h4> 🥕 내 게시물과 함께 팔로우 하는 회원의 게시물과 혹은 내가 팔로우 하지 않은 회원의 게시물 목록 확인 가능</h4>
<h4> 🥕 내가 좋아요 한 게시물 확인 가능</h4>
<h4> 🥕 관리자는 상품리스트, 사용자리스트, 상품리스트 확인 가능</h4>
<br/>

|  |  |
|:--------|:--------:|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139655644-a785c4b3-e735-447c-bbb6-2e9743cb3b35.jpeg"></br><p><strong>메인페이지</strong></p><p>사용자, 포스트, 물품별로 검색 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139655984-f8dfecab-d56f-4e85-a725-4cb9542b9b59.jpg"></br><p><strong>메인페이지</strong></p><p>최근 등록 물품 확인 가능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139656111-5516cf22-5e91-46fd-bb8d-d219e06d8bf8.jpg"></br><p><strong>메인페이지</strong></p><p>가장 인기있는 포스트 확인 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139656357-ff43b3c7-97e7-4e63-be90-879bb152df8c.jpg"></br><p><strong>메인페이지</strong></p><p>사이드바 기능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139656546-63a5d29a-3ec6-4098-86b7-e3d521d769ae.jpeg"></br><p><strong>프로필 페이지</strong></p><p>좋아요 누른 포스트, 자신의 포스트, 프로필 확인, 프로필 업데이트 기능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139656822-36e40f8c-f1e4-457d-aab0-9012a939890b.jpeg"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139656865-d2144225-d5f5-4055-9a98-ed51bb98679a.jpg"></br><p><strong>반응형 웹페이지</strong></p><p>화면 크기에 따라 반응형으로 웹페이지 설계.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139657089-9d464161-cd06-4413-bdb5-845e9e8bc2d7.jpg"></br><p><strong>사용자 액티비티 페이지</strong></p><p>자신의 액티비티 등록,삭제 및 점수 확인 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139657099-58ab47e5-2ba9-49e4-a8fe-9d02610657f7.jpg"></br><p><strong>액티비티 페이지</strong></p><p>액티비티 추가 가능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139657477-02cfe4af-d217-431d-8978-6748b8606bca.jpg"></br><p><strong>액티비티 목록 페이지</strong></p><p>관리자는 액티비티 CRUD 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139657774-32074748-469c-4930-a158-d4abf4b7f224.jpeg"></br><p><strong>게시물 목록 페이지</strong></p><p>게시물 이름으로 검색, 원하는 순서대로 보기 가능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139657858-9c7f5bb6-77be-47f9-8daf-7d07c092dbe7.jpeg"></br><p><strong>사용자 게시물 페이지</strong></p><p>팔로우하는 사용자와 자신의 게시물 확인 가능. </p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139657869-ea5f4c34-e62f-4f4c-b735-6e9a7b836b01.jpeg"></br><p><strong>게시물 페이지</strong></p><p>사용자 자신의 게시물 등록, 수정, 삭제 가능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658084-ec111bd6-5909-4725-b28b-aefb20f79f93.jpg"></br><p><strong>상품 목록 페이지</strong></p><p>키워드, 카테고리 별로 검색 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658186-8097a769-ee17-46bc-9107-2be649c683e2.jpeg"></br><p><strong>제품 상세 페이지</strong></p><p>제품 정보 확인 가능. 카트에 담긴 상품도 확인 가능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658245-897530aa-f875-4f12-97c1-1fda538f8a49.jpg"></br><p><strong>제품 상세 페이지</strong></p><p>리뷰 확인, 등록 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658304-0f056153-7d12-49b4-9021-765c9d5b366f.jpeg"></br><p><strong>주문 페이지</strong></p><p>상품 목록 확인, 페이팔 결제 가능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658437-9eba8e20-22f5-4bb2-b83a-edbc8cadc768.jpeg"></br><p><strong>주문 상세 페이지</strong></p><p>자신의 주문 상세내역 확인 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658511-1bc42e00-a4c5-4804-abf9-bda6d3ff2b9e.jpg"></br><p><strong>리스트 페이지</strong></p><p>관리자는 모든 주문, 상품, 사용자 리스트 확인 가능.</p></div>|
|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658613-2d8f6429-b9ea-4a34-b583-5b4843794146.jpg"></br><p><strong>제품 리스트 페이지</strong></p><p>관리자와 판매자는 상품 수정 가능.</p></div>|<div align="center"><img height="200px" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139658717-94279f65-f479-4ee2-a993-658132bdfc2f.jpeg"></br><p><strong>주문 리스트 페이지</strong></p><p>모든 사용자는 주문 리스트 확인 가능.</p></div>|

<div align="center">
</div>
    <br/>
    <br/>
    <br/>
<div>
    <h2 id="erd">🪜 ERD(Entity Relationship Diagram)</h2>
    <img width="100%" alt="스크린샷 2021-10-02 오후 6 48 23" src="https://user-images.githubusercontent.com/63947424/139576975-4a47c829-4187-4568-b13e-f7ef451a925b.jpg">
</div>
    <br/>
    <br/>
    <br/>
    <br/>
