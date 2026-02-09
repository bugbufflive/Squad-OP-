export default {
  async fetch(request) {
    const html = `
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Squad OP 指令助手 Pro</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body { font-family: 'Inter', system-ui, sans-serif; background-color: #f8fafc; }
        .input-group { transition: all 0.2s; }
        .input-group:focus-within { transform: translateY(-1px); box-shadow: 0 4px 6px -1px rgba(59, 130, 246, 0.1); border-color: #3b82f6; }
        .btn-action:active { transform: scale(0.98); }
        
        /* 滚动条美化 */
        ::-webkit-scrollbar { width: 6px; height: 6px; }
        ::-webkit-scrollbar-track { background: #f1f1f1; }
        ::-webkit-scrollbar-thumb { background: #cbd5e1; border-radius: 3px; }
        ::-webkit-scrollbar-thumb:hover { background: #94a3b8; }
    </style>
</head>
<body class="text-slate-700 pb-10">

    <div class="max-w-5xl mx-auto p-4">
        <header class="flex justify-between items-center mb-6 py-4 border-b border-slate-200">
            <div>
                <h1 class="text-2xl font-bold text-slate-800 flex items-center gap-2">
                    <i class="fa-solid fa-terminal text-blue-600"></i> Squad OP 助手 <span class="text-xs bg-blue-100 text-blue-700 px-2 py-0.5 rounded-full">Pro</span>
                </h1>
                <p class="text-xs text-slate-500 mt-1">自动格式化地图名称 | 本地存储配置 | 中文界面</p>
            </div>
            <button onclick="resetConfig()" class="text-xs text-slate-400 hover:text-red-500 underline">重置所有设置</button>
        </header>

        <div class="grid grid-cols-1 md:grid-cols-12 gap-4 mb-6">
            <div class="md:col-span-3 bg-white p-3 rounded-lg shadow-sm border border-slate-200 input-group">
                <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">Admin Name</label>
                <div class="flex items-center gap-2">
                    <i class="fa-solid fa-user-shield text-slate-300"></i>
                    <input type="text" id="adminName" placeholder="输入管理员ID" class="w-full text-sm outline-none font-medium text-slate-700" oninput="saveAndRender()">
                </div>
            </div>

            <div class="md:col-span-2 bg-white p-3 rounded-lg shadow-sm border border-slate-200 input-group">
                <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">Target ID</label>
                <div class="flex items-center gap-2">
                    <i class="fa-solid fa-hashtag text-slate-300"></i>
                    <input type="text" id="targetID" placeholder="ID/Name" class="w-full text-sm outline-none font-mono font-bold text-blue-600" oninput="renderCmds()">
                </div>
            </div>

            <div class="md:col-span-7 bg-white p-3 rounded-lg shadow-sm border border-slate-200 flex gap-3 items-center">
                <div class="flex-1">
                    <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">预设理由</label>
                    <select id="reasonSelect" class="w-full text-sm bg-transparent outline-none cursor-pointer" onchange="applyReason(this.value)"></select>
                </div>
                <div class="w-px h-8 bg-slate-100"></div>
                <div class="flex-[2]">
                    <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">最终理由</label>
                    <input type="text" id="reasonText" placeholder="填写具体原因..." class="w-full text-sm outline-none text-slate-700" oninput="renderCmds()">
                </div>
            </div>
        </div>
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
            
            <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                <div class="bg-slate-50 px-4 py-3 border-b border-slate-200 flex justify-between items-center">
                    <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-map"></i> 地图层级生成</h2>
                    <div class="flex items-center gap-2">
                         <input type="text" id="mapSearch" placeholder="搜索地图..." class="text-xs p-1 px-2 rounded border focus:border-blue-500 outline-none" oninput="filterMaps()">
                    </div>
                </div>
                
                <div class="p-5 space-y-4">
                    <div class="grid grid-cols-12 gap-2">
                        <div class="col-span-5">
                            <label class="text-[10px] text-slate-400 font-bold uppercase">地图</label>
                            <select id="mapSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition" onchange="renderLayer()"></select>
                        </div>
                        <div class="col-span-4">
                            <label class="text-[10px] text-slate-400 font-bold uppercase">模式</label>
                            <select id="modeSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition" onchange="renderLayer()"></select>
                        </div>
                        <div class="col-span-3">
                            <label class="text-[10px] text-slate-400 font-bold uppercase">层版本</label>
                            <select id="verSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition" onchange="renderLayer()">
                                </select>
                        </div>
                    </div>

                    <div class="bg-slate-50 rounded-lg p-3 border border-slate-100 relative">
                        <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 text-slate-300 text-xs font-bold z-0">VS</div>
                        <div class="grid grid-cols-2 gap-6 relative z-10">
                            <div class="space-y-1">
                                <div class="text-[10px] font-bold text-blue-600 uppercase flex items-center gap-1">
                                    <div class="w-2 h-2 rounded-full bg-blue-500"></div> 阵营 1
                                </div>
                                <select id="facA" class="w-full p-1.5 text-xs border border-blue-100 rounded focus:border-blue-500 outline-none" onchange="renderLayer()"></select>
                                <select id="kitA" class="w-full p-1.5 text-xs border border-blue-100 rounded focus:border-blue-500 outline-none text-slate-500" onchange="renderLayer()"></select>
                            </div>
                            <div class="space-y-1">
                                <div class="text-[10px] font-bold text-red-600 uppercase flex items-center gap-1 justify-end">
                                    阵营 2 <div class="w-2 h-2 rounded-full bg-red-500"></div>
                                </div>
                                <select id="facB" class="w-full p-1.5 text-xs border border-red-100 rounded focus:border-red-500 outline-none text-right" onchange="renderLayer()"></select>
                                <select id="kitB" class="w-full p-1.5 text-xs border border-red-100 rounded focus:border-red-500 outline-none text-slate-500 text-right" onchange="renderLayer()"></select>
                            </div>
                        </div>
                    </div>

                    <div class="space-y-3 pt-2">
                    <div class="cursor-pointer group" onclick="copyText(document.getElementById('cmdChangeLayer').innerText)">
                            <div class="flex justify-between text-xs mb-1">
                                <span class="font-bold text-slate-500">立即切图 (ChangeLayer)</span>
                                <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                            </div>
                            <div class="bg-slate-800 text-green-400 font-mono text-xs p-3 rounded shadow-inner break-all hover:bg-slate-700 transition" id="cmdChangeLayer"></div>
                        </div>

                        <div class="cursor-pointer group" onclick="copyText(document.getElementById('cmdNextLayer').innerText)">
                            <div class="flex justify-between text-xs mb-1">
                                <span class="font-bold text-slate-500">设置下一张 (SetNextLayer)</span>
                                <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                            </div>
                            <div class="bg-slate-800 text-yellow-400 font-mono text-xs p-3 rounded shadow-inner break-all hover:bg-slate-700 transition" id="cmdNextLayer"></div>
                        </div>
                    </div>

                    <div class="flex gap-2 border-t pt-3 mt-2">
                        <button onclick="copyText('AdminEndMatch')" class="flex-1 py-2 bg-red-50 text-red-600 text-xs font-bold rounded border border-red-100 hover:bg-red-100 btn-action">结束本局</button>
                        <button onclick="copyText('AdminRestartMatch')" class="flex-1 py-2 bg-orange-50 text-orange-600 text-xs font-bold rounded border border-orange-100 hover:bg-orange-100 btn-action">重开本局</button>
                    </div>
                </div>
            </div>

            <div class="space-y-6">
                
                <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                    <div class="bg-slate-50 px-4 py-3 border-b border-slate-200">
                        <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-gavel"></i> 玩家处分</h2>
                    </div>
                    <div class="p-4 space-y-3">
                        <div class="p-2 border rounded hover:border-blue-400 cursor-pointer transition group bg-white" onclick="copyFromId('kickCmd')">
                            <div class="flex justify-between items-center mb-1">
                                <span class="text-xs font-bold text-slate-500">Kick (踢出)</span>
                                <i class="fa-regular fa-copy text-slate-300 group-hover:text-blue-500"></i>
                            </div>
                            <code id="kickCmd" class="text-xs text-slate-800 font-mono block break-all"></code>
                        </div>

                        <div class="p-2 border rounded hover:border-red-400 cursor-pointer transition group bg-white relative" onclick="copyFromId('banCmd')">
                            <div class="flex justify-between items-center mb-1">
                                <span class="text-xs font-bold text-slate-500">Ban (封禁)</span>
                                <select id="banTime" class="text-[10px] border rounded bg-slate-50 px-1 py-0.5 outline-none cursor-pointer hover:bg-white" onchange="renderCmds()" onclick="event.stopPropagation()">
                                    <option value="1d">1天</option>
                                    <option value="3d">3天</option>
                                    <option value="1w">1周</option>
                                    <option value="1M">1月</option>
                                    <option value="0">永久</option>
                                </select>
                            </div>
                            <code id="banCmd" class="text-xs text-red-600 font-mono block break-all"></code>
                        </div>
                        
                        <div class="p-2 border rounded hover:border-yellow-400 cursor-pointer transition group bg-white" onclick="copyFromId('warnCmd')">
                            <div class="flex justify-between items-center mb-1">
                                <span class="text-xs font-bold text-slate-500">Warn (警告)</span>
                                <i class="fa-regular fa-copy text-slate-300 group-hover:text-yellow-500"></i>
                            </div>
                            <code id="warnCmd" class="text-xs text-yellow-600 font-mono block break-all"></code>
                        </div>
                    </div>
                </div>

                <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                    <div class="bg-slate-50 px-4 py-3 border-b border-slate-200">
                        <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-users-gear"></i> 队伍与小队</h2>
                    </div>
                    <div class="p-4">
                        <div class="flex gap-2 mb-3">
                            <input type="number" id="teamId" placeholder="阵营 ID (1/2)" class="w-1/2 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                            <input type="number" id="squadId" placeholder="小队 ID" class="w-1/2 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                        </div>
                        <div class="grid grid-cols-2 gap-2">
                            <button onclick="quickCmd('AdminForceTeamChangebyid', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                                <i class="fa-solid fa-right-left mr-1"></i> 强切阵营
                            </button>
                            <button onclick="quickCmd('AdminRemovePlayerFromSquadbyid', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                                <i class="fa-solid fa-user-xmark mr-1"></i> 踢出小队
                            </button>
                            <button onclick="quickCmd('AdminDemoteCommanderbyid', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                                <i class="fa-solid fa-user-slash mr-1"></i> 卸任指挥
                            </button>
                            <button onclick="getDisbandCmd()" class="p-2 bg-red-50 text-red-700 text-xs font-bold rounded hover:bg-red-100 text-left btn-action">
                                <i class="fa-solid fa-ban mr-1"></i> 解散小队
                            </button>
                        </div>
                        <div class="mt-3 pt-3 border-t border-slate-100 grid grid-cols-3 gap-2">
                            <button onclick="copyText('AdminDisableVehicleClaiming 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">
                                <i class="fa-solid fa-car"></i> 取消载具认领
                            </button>
                            <button onclick="copyText('AdminNoRespawnTimer 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">
                                <i class="fa-solid fa-clock"></i> 取消复活时间
                            </button>
                            <button onclick="copyText('AdminForceAllRoleAvailability 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">
                                <i class="fa-solid fa-user-group"></i> 解除装备限制
                            </button>
                            <button onclick="copyText('AdminDisableVehicleClaiming 0')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">
                                 <i class="fa-solid fa-lock-open"></i> 解锁载具 
                             </button>
                             <button onclick="copyText('AdminAllKitsAvailable 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">
                                 <i class="fa-solid fa-person-rifle"></i> 解锁兵种 
                             </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div id="toast" class="fixed top-5 left-1/2 -translate-x-1/2 bg-slate-800 text-white px-4 py-2 rounded-full shadow-lg transform transition-all duration-300 translate-y-[-150%] opacity-0 flex items-center gap-2 z-50">
        <i class="fa-solid fa-check-circle text-green-400"></i>
        <span class="text-sm font-medium">指令已复制</span>
    </div>

<script>
    // ================= CONFIG DATA =================
    const CONFIG = {
        // 地图：中文显示文本，英文值
        maps: [
            { label: "巴士拉", value: "AlBasrah" },
            { label: "铁砧", value: "Anvil" },
            { label: "贝拉亚山口", value: "BelayaPass" },
            { label: "黑色海岸", value: "BlackCoast" },
            { label: "乔拉", value: "Chora" },
            { label: "费卢杰", value: "Fallujah" },
            { label: "愚者之路", value: "FoolsRoad" },
            { label: "鹅湾", value: "GooseBay" },
            { label: "格罗多克", value: "Gorodok" },
            { label: "哈留", value: "Harju" },
            { label: "詹森训练场", value: "JensensRange" },
            { label: "卡玛德仕高地", value: "KamdeshHighlands" },
            { label: "科哈特", value: "KohatToi" },
            { label: "寇坎", value: "Kokan" },
            { label: "拉什喀河谷", value: "LashkarValley" },
            { label: "珞珈山谷", value: "LogarValley" },
            { label: "曼尼古根", value: "Manicouagan" },
            { label: "梅斯蒂亚", value: "Mestia" },
            { label: "穆塔哈", value: "Mutaha" },
            { label: "纳尔瓦", value: "Nara" },
            { label: "太平洋训练场", value: "PacificProvingGrounds" },
            { label: "三贤群岛", value: "Sanxian" },
            { label: "斯科普", value: "Skorpo" },
            { label: "苏玛瑞", value: "SumariBala" },
            { label: "塔利尔郊区", value: "TalilOutskirts" },
            { label: "叶霍里夫卡", value: "Yehorivka" }
        ],
        // 模式：中文显示文本，英文值
        modes: [
            { label: "遭遇战", value: "Skirmish" },
            { label: "随机攻防", value: "RAAS" },
            { label: "侵攻", value: "Invasion" },
            { label: "叛乱", value: "Insurgency" },
            { label: "攻守有序", value: "AAS" },
            { label: "摧毁", value: "Destruction" },
            { label: "区域控制", value: "TC" },
            { label: "火力侦察", value: "Seed" }
        ],
        // 阵营：中文显示文本，英文值
        factions: [
            { label: "人民解放军", value: "PLA" },
            { label: "人民解放军陆军两栖旅", value: "PLAAGF" },
            { label: "人民解放军海军陆战队", value: "PLANMC" },
            { label: "美国陆军", value: "USA" },
            { label: "美国海军陆战队", value: "USMC" },
            { label: "俄罗斯陆军", value: "RGF" },
            { label: "俄罗斯空降军", value: "VDV" },
            { label: "澳大利亚国防军", value: "ADF" },
            { label: "英国陆军", value: "BAF" },
            { label: "非正规民兵", value: "CAF" },
            { label: "中东联军", value: "MEA" },
            { label: "土耳其陆军", value: "TLF" },
            { label: "乌克兰陆军", value: "AFU" },
            { label: "非正规民兵部队", value: "IMF" },
            { label: "叛乱分子", value: "INS" },
            { label: "加拿大抵抗力量", value: "CRF" }
        ],
        // 兵种：中文显示文本，英文值
        kits: [
            { label: "合成化", value: "CombinedArms" },
            { label: "空中突击", value: "AirAssault" },
            { label: "装甲", value: "Armored" },
            { label: "轻步兵", value: "LightInfantry" },
            { label: "机械化", value: "Mechanized" },
            { label: "摩托化", value: "Motorized" },
            { label: "勤务保障", value: "Support" }
        ],
        reasons: ["外挂", "无麦带队", "毒瘤"]
    };

    // ================= INITIALIZATION =================
    window.onload = function() {
        // Load Maps
        populateSelect('mapSelect', CONFIG.maps, "Mutaha");
        populateSelect('modeSelect', CONFIG.modes, "RAAS");
        
        // Load Versions (v1-v3)
        const verSel = document.getElementById('verSelect');
        for(let i = 1; i <= 3; i++) {
            verSel.add(new Option(\`v\${i}\`, \`v\${i}\`));
        }
        
        // Load Factions & Kits
        populateSelect('facA', CONFIG.factions, "USA");
        populateSelect('facB', CONFIG.factions, "RGF");
        populateSelect('kitA', CONFIG.kits, "CombinedArms");
        populateSelect('kitB', CONFIG.kits, "CombinedArms");
        
        // Load Reasons
        populateSelect('reasonSelect', CONFIG.reasons);
        document.getElementById('reasonSelect').value = ""; // Default empty

        // Load LocalStorage
        const savedAdmin = localStorage.getItem('squad_op_admin');
        if(savedAdmin) document.getElementById('adminName').value = savedAdmin;
        
        // Initial Render
        renderCmds();
        renderLayer();
    };

    // ================= CORE FUNCTIONS =================
    
    function populateSelect(id, list, defValue = null, useLabelAsValue = false) {
        const sel = document.getElementById(id);
        sel.innerHTML = "";
        
        list.forEach(item => {
            let label, value;
            
            if (typeof item === 'object') {
                label = item.label;
                value = item.value;
            } else {
                label = item;
                value = item;
            }
            
            const option = new Option(label, value);
            sel.add(option);
            
            // 设置默认值
            if (defValue && (value === defValue || label === defValue)) {
                sel.value = value;
            }
        });
    }

    // 搜索过滤地图
    function filterMaps() {
        const term = document.getElementById('mapSearch').value.toLowerCase();
        const sel = document.getElementById('mapSelect');
        sel.innerHTML = "";
        
        CONFIG.maps.forEach(map => {
            if(map.label.toLowerCase().includes(term) || map.value.toLowerCase().includes(term)) {
                sel.add(new Option(map.label, map.value));
            }
        });
        renderLayer();
    }

    function applyReason(val) {
        if(!val) return;
        document.getElementById('reasonText').value = val;
        renderCmds();
    }

    function saveAndRender() {
        const admin = document.getElementById('adminName').value;
        localStorage.setItem('squad_op_admin', admin);
        renderCmds();
    }

    function renderCmds() {
        const admin = document.getElementById('adminName').value.trim() || "Admin";
        const id = document.getElementById('targetID').value.trim() || "<ID>";
        const reason = document.getElementById('reasonText').value.trim() || "违反服务器规则";
        const banTime = document.getElementById('banTime').value;
        
        // 构造理由字符串
        const finalReason = \`\${reason} [处理人:\${admin}]\`;
        
        // 生成指令
        document.getElementById('kickCmd').innerText = \`Adminkickbyid "\${id}" \${finalReason}\`;
        document.getElementById('banCmd').innerText = \`Adminbanbyid "\${id}" \${banTime} \${finalReason}\`;
        document.getElementById('warnCmd').innerText = \`AdminWarn "\${id}" \${reason}\`;
    }

    function renderLayer() {
        // 获取原始值
        const map = document.getElementById('mapSelect').value; // 已经是英文值
        const mode = document.getElementById('modeSelect').value; // 已经是英文值
        const ver = document.getElementById('verSelect').value;
        
        const fa = document.getElementById('facA').value; // 已经是英文值
        const ka = document.getElementById('kitA').value; // 已经是英文值
        const fb = document.getElementById('facB').value; // 已经是英文值
        const kb = document.getElementById('kitB').value; // 已经是英文值

        // 拼接图层字符串
        const layerStr = \`\${map}_\${mode}_\${ver} \${fa}+\${ka} \${fb}+\${kb}\`;

        document.getElementById('cmdChangeLayer').innerText = \`AdminChangeLayer \${layerStr}\`;
        document.getElementById('cmdNextLayer').innerText = \`AdminSetNextLayer \${layerStr}\`;
    }

    // ================= UTILS =================

    function copyFromId(id) {
        copyText(document.getElementById(id).innerText);
    }

    function quickCmd(prefix, inputId) {
        const v = document.getElementById(inputId).value || "";
        if(!v) { alert("请输入目标ID"); return; }
        copyText(\`\${prefix} \${v}\`);
    }

    function getDisbandCmd() {
        const t = document.getElementById('teamId').value;
        const s = document.getElementById('squadId').value;
        if(!t || !s) { alert("请先填写阵营ID和小队ID"); return; }
        copyText(\`AdminDisbandSquad \${t} \${s}\`);
    }
    
    function resetConfig() {
        if(confirm('确定要重置所有设置吗？')) {
            localStorage.clear();
            location.reload();
        }
    }

    async function copyText(text) {
        try {
            await navigator.clipboard.writeText(text);
            showToast();
        } catch (err) {
            // Fallback for older browsers
            const textArea = document.createElement("textarea");
            textArea.value = text;
            document.body.appendChild(textArea);
            textArea.select();
            document.execCommand("copy");
            document.body.removeChild(textArea);
            showToast();
        }
    }

    function showToast() {
        const t = document.getElementById('toast');
        t.classList.remove('translate-y-[-150%]', 'opacity-0');
        t.classList.add('translate-y-0', 'opacity-100');
        
        setTimeout(() => {
            t.classList.remove('translate-y-0', 'opacity-100');
            t.classList.add('translate-y-[-150%]', 'opacity-0');
        }, 2000);
    }
</script>
</body>
</html>
    `;

    return new Response(html, {
      headers: { "content-type": "text/html;charset=UTF-8" },
    });
  },
};