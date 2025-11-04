// Data
const mockGyms = [
    {
        id: 1,
        name: '프리미엄 피트니스 강남점',
        location: '서울 강남구',
        rating: 4.8,
        reviews: 342,
        image: 'https://images.unsplash.com/photo-1760019737803-1f862ba9faf9?w=800',
        equipment: ['스미스머신', '케이블머신', '덤벨 세트', '런닝머신', '사이클'],
        trainers: 12,
        programs: ['근력강화', '체중감량', '재활운동'],
        price: '₩120,000/월',
        highlights: ['최신 기구', '24시간 운영', '샤워실 완비']
    },
    {
        id: 2,
        name: '파워하우스 짐 홍대점',
        location: '서울 마포구',
        rating: 4.6,
        reviews: 218,
        image: 'https://images.unsplash.com/photo-1632077804406-188472f1a810?w=800',
        equipment: ['파워랙', '벤치프레스', '데드리프트존', '올림픽 바벨'],
        trainers: 8,
        programs: ['파워리프팅', '보디빌딩', '크로스핏'],
        price: '₩95,000/월',
        highlights: ['파워리프팅 특화', '전문 코치진', '주차 가능']
    },
    {
        id: 3,
        name: '바디쉐이프 센터 판교점',
        location: '경기 성남시',
        rating: 4.9,
        reviews: 456,
        image: 'https://images.unsplash.com/photo-1760019737803-1f862ba9faf9?w=800',
        equipment: ['TRX', 'GHD', '케틀벨', '플라이오박스', '로잉머신'],
        trainers: 15,
        programs: ['기능성운동', '체형교정', '산후관리'],
        price: '₩150,000/월',
        highlights: ['여성전용', '키즈룸', 'GX 프로그램']
    },
    {
        id: 4,
        name: '애슬리트 짐 잠실점',
        location: '서울 송파구',
        rating: 4.7,
        reviews: 389,
        image: 'https://images.unsplash.com/photo-1632077804406-188472f1a810?w=800',
        equipment: ['해머스트렝스', '레그프레스', '힙쓰러스트머신', '케이블크로스'],
        trainers: 10,
        programs: ['스포츠퍼포먼스', '근비대', '컨디셔닝'],
        price: '₩110,000/월',
        highlights: ['프리미엄 기구', '운동선수 관리', '영양 상담']
    }
];

const mockMembers = [
    { id: 1, name: '김민수', program: '근력강화', sessions: 8, total: 12, progress: 67 },
    { id: 2, name: '이지은', program: '체중감량', sessions: 15, total: 20, progress: 75 },
    { id: 3, name: '박준혁', program: '재활운동', sessions: 5, total: 10, progress: 50 },
    { id: 4, name: '최서연', program: '체형교정', sessions: 18, total: 24, progress: 75 }
];

const mockEquipment = [
    { id: 1, name: '스미스머신', category: '프리웨이트', count: 2, status: 'good', lastMaintenance: '2025-09-15' },
    { id: 2, name: '케이블머신', category: '머신', count: 3, status: 'good', lastMaintenance: '2025-09-20' },
    { id: 3, name: '런닝머신', category: '유산소', count: 10, status: 'maintenance', lastMaintenance: '2025-08-30' },
    { id: 4, name: '덤벨 세트', category: '프리웨이트', count: 15, status: 'good', lastMaintenance: '2025-10-01' },
    { id: 5, name: '벤치프레스', category: '프리웨이트', count: 2, status: 'warning', lastMaintenance: '2025-07-15' },
    { id: 6, name: '레그프레스', category: '머신', count: 2, status: 'good', lastMaintenance: '2025-09-25' }
];

// App State
let currentView = 'home';
let filteredGyms = [...mockGyms];

// Initialize App
function init() {
    renderView(currentView);
    setupEventListeners();
}

// Navigation
function navigateTo(view) {
    currentView = view;
    renderView(view);
    window.scrollTo(0, 0);
}

// Render Views
function renderView(view) {
    const app = document.getElementById('app');
    
    switch(view) {
        case 'home':
            renderHome(app);
            break;
        case 'member':
            renderGymExplorer(app);
            break;
        case 'trainer':
            renderTrainerDashboard(app);
            break;
        case 'gym':
            renderGymDashboard(app);
            break;
    }
    
    setupEventListeners();
}

function renderHome(container) {
    const template = document.getElementById('home-template');
    const clone = template.content.cloneNode(true);
    container.innerHTML = '';
    container.appendChild(clone);
}

function renderGymExplorer(container) {
    const template = document.getElementById('gym-explorer-template');
    const clone = template.content.cloneNode(true);
    container.innerHTML = '';
    container.appendChild(clone);
    
    // Render gym list
    renderGymList(filteredGyms);
    
    // Setup search
    const searchInput = document.getElementById('gym-search');
    searchInput.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        filteredGyms = mockGyms.filter(gym => 
            gym.name.toLowerCase().includes(query) ||
            gym.location.toLowerCase().includes(query) ||
            gym.equipment.some(eq => eq.toLowerCase().includes(query))
        );
        renderGymList(filteredGyms);
    });
}

function renderGymList(gyms) {
    const gymList = document.getElementById('gym-list');
    const gymCount = document.getElementById('gym-count');
    
    gymCount.textContent = `${gyms.length}개의 헬스장을 찾았습니다`;
    
    gymList.innerHTML = gyms.map(gym => `
        <div class="card card-hover gym-card">
            <img src="${gym.image}" alt="${gym.name}" class="gym-card-image">
            <div class="gym-card-content">
                <h3 class="mb-2">${gym.name}</h3>
                <div class="flex items-center gap-2 text-muted mb-3">
                    <svg class="icon-sm" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/>
                        <circle cx="12" cy="10" r="3"/>
                    </svg>
                    <span>${gym.location}</span>
                </div>
                <div class="flex items-center gap-4 mb-4">
                    <div class="flex items-center gap-1">
                        <span style="color: #fbbf24;">⭐</span>
                        <span>${gym.rating}</span>
                    </div>
                    <div class="flex items-center gap-1">
                        <svg class="icon-sm text-muted" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
                            <circle cx="9" cy="7" r="4"/>
                        </svg>
                        <span class="text-muted">트레이너 ${gym.trainers}명</span>
                    </div>
                </div>
                <div class="gym-card-badges">
                    ${gym.equipment.slice(0, 3).map(eq => `<span class="badge">${eq}</span>`).join('')}
                    ${gym.equipment.length > 3 ? `<span class="badge">+${gym.equipment.length - 3}</span>` : ''}
                </div>
                <div class="gym-card-footer">
                    <span class="text-blue" style="font-weight: 600;">${gym.price}</span>
                    <button class="btn btn-ghost btn-sm">
                        자세히 보기
                        <svg class="icon-sm" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <path d="M5 12h14M12 5l7 7-7 7"/>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    `).join('');
}

function renderTrainerDashboard(container) {
    const template = document.getElementById('trainer-dashboard-template');
    const clone = template.content.cloneNode(true);
    container.innerHTML = '';
    container.appendChild(clone);
    
    // Render member list
    const memberList = document.getElementById('member-list');
    memberList.innerHTML = mockMembers.map(member => `
        <div class="card member-card">
            <div class="member-header">
                <div class="member-info">
                    <div class="member-avatar"></div>
                    <div>
                        <p class="mb-1" style="font-weight: 600;">${member.name}</p>
                        <span class="badge">${member.program}</span>
                    </div>
                </div>
                <button class="btn btn-ghost btn-sm">상세</button>
            </div>
            <div>
                <div class="flex justify-between text-muted mb-2">
                    <span>진행률</span>
                    <span>${member.sessions}/${member.total} 세션</span>
                </div>
                <div class="progress-bar">
                    <div class="progress-fill" style="width: ${member.progress}%"></div>
                </div>
            </div>
        </div>
    `).join('');
}

function renderGymDashboard(container) {
    const template = document.getElementById('gym-dashboard-template');
    const clone = template.content.cloneNode(true);
    container.innerHTML = '';
    container.appendChild(clone);
    
    // Render equipment list
    const equipmentList = document.getElementById('equipment-list');
    equipmentList.innerHTML = mockEquipment.map(equipment => {
        let statusBadge = '';
        if (equipment.status === 'good') {
            statusBadge = `<span class="status-badge status-good">
                <svg class="icon-sm" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"/>
                    <polyline points="22 4 12 14.01 9 11.01"/>
                </svg>
                양호
            </span>`;
        } else if (equipment.status === 'warning') {
            statusBadge = `<span class="status-badge status-warning">
                <svg class="icon-sm" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <circle cx="12" cy="12" r="10"/>
                    <line x1="12" y1="8" x2="12" y2="12"/>
                    <line x1="12" y1="16" x2="12.01" y2="16"/>
                </svg>
                점검 필요
            </span>`;
        } else if (equipment.status === 'maintenance') {
            statusBadge = `<span class="status-badge status-maintenance">
                <svg class="icon-sm" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M14.7 6.3a1 1 0 0 0 0 1.4l1.6 1.6a1 1 0 0 0 1.4 0l3.77-3.77a6 6 0 0 1-7.94 7.94l-6.91 6.91a2.12 2.12 0 0 1-3-3l6.91-6.91a6 6 0 0 1 7.94-7.94l-3.76 3.76z"/>
                </svg>
                정비 중
            </span>`;
        }
        
        return `
            <div class="card equipment-card">
                <div class="equipment-header">
                    <div class="equipment-info">
                        <div class="equipment-icon">
                            <svg class="icon text-white" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                                <path d="M6.5 6.5l11 11M17.5 6.5l-11 11M3 12h3M18 12h3M12 3v3M12 18v3"/>
                            </svg>
                        </div>
                        <div>
                            <p class="mb-1" style="font-weight: 600;">${equipment.name}</p>
                            <p class="text-muted">${equipment.category} • 수량: ${equipment.count}</p>
                        </div>
                    </div>
                    ${statusBadge}
                </div>
                <div class="equipment-footer">
                    <div class="flex items-center gap-2 text-muted">
                        <svg class="icon-sm" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                            <circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/>
                        </svg>
                        <span>최근 점검: ${equipment.lastMaintenance}</span>
                    </div>
                    <button class="btn btn-ghost btn-sm">관리</button>
                </div>
            </div>
        `;
    }).join('');
}

// Event Listeners
function setupEventListeners() {
    // Navigation buttons
    document.querySelectorAll('[data-navigate]').forEach(btn => {
        btn.addEventListener('click', (e) => {
            const view = e.currentTarget.getAttribute('data-navigate');
            navigateTo(view);
        });
    });
    
    // Back buttons
    document.querySelectorAll('[data-back]').forEach(btn => {
        btn.addEventListener('click', () => {
            navigateTo('home');
        });
    });
}

// Start the app
document.addEventListener('DOMContentLoaded', init);
