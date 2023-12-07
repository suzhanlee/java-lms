# 학습 관리 시스템(Learning Management System)

## 진행 방법

* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## LMS STEP 1 기능 목록

* `Question`
    * [x] : 로그인 사용자, 질문자와 모든 답변자들이 같으면 답변을 삭제 상태로 바꿀 수 있다.
        * [x] : 질문이 삭제되면 삭제 히스토리에게 질문 삭제 요청을 보낸다.
        * [x] : 질문이 삭제되면 삭제 히스토리에게 질문에 대한 댓글들의 삭제 요청을 보낸다.
    * [x] : 삭제할 수 없다면 예외를 던진다.
        * [x] : 사용자와 질문자가 달라 삭제할 수 없다면 예외를 던진다.
        * [x] : 질문자와 답변자들이 달라 삭제할 수 없다면 예외를 던진다.

* `Answer`
    * [x] : 질문자와 답변자가 같은지 다른지 확인한다.
    * [x] : 질문이 삭제되면, 댓글도 삭제한다.

## 수강 신청 기능 요구사항

* 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
* 강의는 시작일과 종료일을 가진다.
* 강의는 강의 커버 이미지 정보를 가진다.
* 이미지 크기는 1MB 이하여야 한다.
* 이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
* 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
* 강의는 무료 강의와 유료 강의로 나뉜다.
* 무료 강의는 최대 수강 인원 제한이 없다.
* 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
* 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
* 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
* 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
* 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
* 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

## LMS STEP 2 기능 목록

* `Course` : 과정
    * [] : 

* `Session` : 강의
    * [] :

* `SessionStatus` : 강의 상태
    * [] : 강의 수강 신청은 강의 상태가 모집중 일 때만 가능하다.

* `SessionType` : 강의 타입
    * [] : 강의 타입이 유료인 경우, 강의 신청 시 수강 인원이 다 찼는지 알려준다.
    * [] : 결제 금액과 수강 금액이 동일할 때, 수강이 가능하다.

* `Image` : 이미지
    * [] : 

* `ImageSize` : 이미지 사이즈
    * [x] : 이미지의 width는 300픽셀 이상인지 검증한다.
    * [x] : 이미지의 height는 200픽셀 이상인지 검증한다.
    * [x] : width와 height의 비율은 3:2 인지 검증한다.

* `ImageType` : 이미지 타입
    * [x] : 사용할 수 있는 이미지 타입인지 검증한다.

* `ImageCapacityType` : 이미지 용량 타입
    * [x] : 이미지 용량 이름으로 이미지 용량 타입을 찾는다.

* `ImageCapacity` : 이미지 용량
    * [x] : 이미지 용량이 1MB 이하인지 검증한다.

* `Payments` : 결제 정보들
    * [] : 

* `Payment` : 결제 정보
    * [] :



