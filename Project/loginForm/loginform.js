// 로그인 상태 관리
let isLoggedIn = false;
let currentUser = '';

// 모달 요소들
const loginBtn = document.getElementById('loginBtn');
const signupBtn = document.getElementById('signupBtn');
const logoutBtn = document.getElementById('logoutBtn');
const loginModal = document.getElementById('loginModal');
const signupModal = document.getElementById('signupModal');
const closeLoginModal = document.getElementById('closeLoginModal');
const closeModal = document.getElementById('closeModal');
const welcomeMessage = document.getElementById('welcomeMessage');
const goToSignup = document.getElementById('goToSignup');

// 로그인 모달 열기/닫기
loginBtn.addEventListener('click', () => {
    loginModal.classList.add('active');
});

closeLoginModal.addEventListener('click', () => {
    loginModal.classList.remove('active');
});

loginModal.addEventListener('click', (e) => {
    if (e.target === loginModal) {
        loginModal.classList.remove('active');
    }
});

// 회원가입 모달 열기/닫기
signupBtn.addEventListener('click', () => {
    signupModal.classList.add('active');
});

closeModal.addEventListener('click', () => {
    signupModal.classList.remove('active');
});

signupModal.addEventListener('click', (e) => {
    if (e.target === signupModal) {
        signupModal.classList.remove('active');
    }
});

// 로그인에서 회원가입으로 이동
goToSignup.addEventListener('click', () => {
    loginModal.classList.remove('active');
    signupModal.classList.add('active');
});

// 로그인 폼 제출
document.getElementById('loginForm').addEventListener('submit', (e) => {
    e.preventDefault();
    
    const userId = document.getElementById('loginId').value;
    const userPassword = document.getElementById('loginPassword').value;
    
    // DB 연결 전이므로 임시로 아무 아이디/비번이나 받아서 로그인 처리
    if (userId && userPassword) {
        isLoggedIn = true;
        currentUser = userId;
        
        // UI 업데이트
        updateLoginUI();
        
        // 모달 닫기
        loginModal.classList.remove('active');
        
        // 폼 리셋
        document.getElementById('loginForm').reset();
        
        alert(`${currentUser}님, 환영합니다!`);
    }
});

// 로그아웃
logoutBtn.addEventListener('click', () => {
    isLoggedIn = false;
    currentUser = '';
    updateLoginUI();
    alert('로그아웃 되었습니다.');
});

// 로그인 UI 업데이트
function updateLoginUI() {
    if (isLoggedIn) {
        loginBtn.style.display = 'none';
        signupBtn.style.display = 'none';
        welcomeMessage.textContent = `${currentUser}님 환영합니다`;
        welcomeMessage.style.display = 'inline';
        logoutBtn.style.display = 'inline-block';
    } else {
        loginBtn.style.display = 'inline-block';
        signupBtn.style.display = 'inline-block';
        welcomeMessage.style.display = 'none';
        logoutBtn.style.display = 'none';
    }
}

// 탭 전환 기능
const tabs = document.querySelectorAll('.tab');
const tabContents = document.querySelectorAll('.tab-content');

tabs.forEach(tab => {
    tab.addEventListener('click', function() {
        const targetTab = this.getAttribute('data-tab');
        
        tabs.forEach(t => t.classList.remove('active'));
        tabContents.forEach(c => c.classList.remove('active'));
        
        this.classList.add('active');
        document.getElementById(targetTab).classList.add('active');
    });
});

// 폼 제출 처리
document.getElementById('submitBtn').addEventListener('click', function(e) {
    e.preventDefault();
    const activeForm = document.querySelector('.tab-content.active .registration-form');
    if (activeForm.checkValidity()) {
        alert('회원가입이 완료되었습니다!');
        signupModal.classList.remove('active');
    } else {
        activeForm.reportValidity();
    }
});

// 비밀번호 확인 검증
document.querySelectorAll('.registration-form').forEach(form => {
    const passwordInput = form.querySelector('.password');
    const confirmInput = form.querySelector('.password-confirm');
    const errorText = confirmInput.parentElement.querySelector('.helper-text.error');

    confirmInput.addEventListener('input', function() {
        const password = passwordInput.value;
        const confirmPassword = this.value;
        
        if (confirmPassword && password !== confirmPassword) {
            errorText.style.display = 'block';
        } else {
            errorText.style.display = 'none';
        }
    });
});

// 검색 기능
const searchInput = document.querySelector('.search-input');
const searchBtn = document.querySelector('.search-btn');
const filterSelect = document.querySelector('.filter-select');
const gymCards = document.querySelectorAll('.gym-card');

// 검색 실행 함수
function performSearch() {
    const searchTerm = searchInput.value.toLowerCase().trim();
    const sortOption = filterSelect.value;
    
    let visibleCards = [];
    
    // 검색어로 필터링
    gymCards.forEach(card => {
        const title = card.querySelector('.gym-title').textContent.toLowerCase();
        const location = card.querySelector('.gym-location').textContent.toLowerCase();
        const description = card.querySelector('.gym-description').textContent.toLowerCase();
        
        if (searchTerm === '' || 
            title.includes(searchTerm) || 
            location.includes(searchTerm) || 
            description.includes(searchTerm)) {
            card.style.display = 'block';
            visibleCards.push(card);
        } else {
            card.style.display = 'none';
        }
    });
    
    // 정렬
    if (sortOption && visibleCards.length > 0) {
        sortCards(visibleCards, sortOption);
    }
    
    // 검색 결과 메시지
    showSearchResults(visibleCards.length, searchTerm);
}

// 카드 정렬 함수
function sortCards(cards, sortOption) {
    const cardsGrid = document.querySelector('.cards-grid');
    const cardsArray = Array.from(cards);
    
    cardsArray.sort((a, b) => {
        switch(sortOption) {
            case 'rating':
                const ratingA = parseFloat(a.querySelector('.gym-rating').textContent.match(/[\d.]+/)[0]);
                const ratingB = parseFloat(b.querySelector('.gym-rating').textContent.match(/[\d.]+/)[0]);
                return ratingB - ratingA;
            
            case 'price-low':
                const priceA = parseInt(a.querySelector('.gym-price').textContent.replace(/[^\d]/g, ''));
                const priceB = parseInt(b.querySelector('.gym-price').textContent.replace(/[^\d]/g, ''));
                return priceA - priceB;
            
            case 'price-high':
                const priceA2 = parseInt(a.querySelector('.gym-price').textContent.replace(/[^\d]/g, ''));
                const priceB2 = parseInt(b.querySelector('.gym-price').textContent.replace(/[^\d]/g, ''));
                return priceB2 - priceA2;
            
            case 'review':
                const reviewA = parseInt(a.querySelector('.gym-rating').textContent.match(/\((\d+)\)/)[1]);
                const reviewB = parseInt(b.querySelector('.gym-rating').textContent.match(/\((\d+)\)/)[1]);
                return reviewB - reviewA;
            
            default:
                return 0;
        }
    });
    
    // 정렬된 순서대로 DOM에 다시 추가
    cardsArray.forEach(card => {
        cardsGrid.appendChild(card);
    });
}

// 검색 결과 메시지 표시
function showSearchResults(count, searchTerm) {
    let resultMessage = document.querySelector('.search-result-message');
    
    if (!resultMessage) {
        resultMessage = document.createElement('div');
        resultMessage.className = 'search-result-message';
        document.querySelector('.cards-section').insertBefore(
            resultMessage, 
            document.querySelector('.cards-grid')
        );
    }
    
    if (searchTerm) {
        if (count === 0) {
            resultMessage.textContent = `"${searchTerm}"에 대한 검색 결과가 없습니다.`;
            resultMessage.style.display = 'block';
        } else {
            resultMessage.textContent = `"${searchTerm}" 검색 결과: ${count}개`;
            resultMessage.style.display = 'block';
        }
    } else {
        resultMessage.style.display = 'none';
    }
}

// 검색 버튼 클릭
searchBtn.addEventListener('click', performSearch);

// 엔터키로 검색
searchInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        performSearch();
    }
});

// 필터 변경 시 자동 검색
filterSelect.addEventListener('change', performSearch);