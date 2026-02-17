export default {
  async fetch(request) {
    const html = `<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Squad OP 指令助手 Pro </title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <!-- 网站Favicon (SVG) -->
    <link rel="icon" href="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16'%3E%3Ccircle cx='8' cy='8' r='7' fill='%233b82f6'/%3E%3Ctext x='8' y='12' font-size='9' text-anchor='middle' fill='white' font-family='Arial' font-weight='bold'%3ES%3C/text%3E%3C/svg%3E">
    <style>
        body { font-family: 'Inter', system-ui, sans-serif; background-color: #f8fafc; min-height: 100vh; }
        body {background-image: url('https://img.quad.de5.net/file/hf/xkZ3xD1M.png') !important;background-size: cover !important;background-position: center !important;background-attachment: fixed !important;}
        .input-group { transition: all 0.2s; }
        .input-group:focus-within { transform: translateY(-1px); box-shadow: 0 4px 6px -1px rgba(59,130,246,0.1); border-color: #3b82f6; }
        .btn-action:active { transform: scale(0.98); }
        .cmd-display .cmd-command { color: #60a5fa; font-weight: 500; }
        .cmd-display .cmd-string { color: #4ade80; }
        ::-webkit-scrollbar { width: 6px; height: 6px; }
        ::-webkit-scrollbar-track { background: #f1f1f1; }
        ::-webkit-scrollbar-thumb { background: #cbd5e1; border-radius: 3px; }
        .history-item { background: #f1f5f9; border-radius: 12px; padding: 0.25rem 0.75rem; font-size: 0.7rem; max-width: 150px; overflow: hidden; text-overflow: ellipsis; cursor: pointer; }
        .history-item:hover { background: #e2e8f0; }
    </style>
</head>
<body class="text-slate-700 pb-10">

<div class="max-w-5xl mx-auto p-4">
    <header class="flex flex-wrap justify-between items-center mb-6 py-4 border-b border-slate-200 gap-2">
        <div>
            <h1 class="text-2xl font-bold text-slate-800 flex items-center gap-2">
                <!-- 网站Logo图片 (SVG) -->
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 32 32'%3E%3Ccircle cx='16' cy='16' r='14' fill='%232563eb'/%3E%3Ctext x='16' y='23' font-size='18' text-anchor='middle' fill='white' font-family='Arial' font-weight='bold'%3ES%3C/text%3E%3C/svg%3E" class="w-6 h-6 mr-1" alt="logo">
                <i class="fa-solid fa-terminal text-blue-600"></i> Squad OP 助手 <span class="text-xs bg-blue-100 text-blue-700 px-2 py-0.5 rounded-full">Pro</span>
            </h1>
            <p class="text-xs text-slate-500 mt-1">自动格式化 | 本地存储 | 理由自定义 | 指令历史 | 防抖优化</p>
        </div>
        <div>
            <button onclick="app.resetConfig()" class="text-xs text-slate-400 hover:text-red-500 underline ml-1">重置所有设置</button>
        </div>
    </header>

    <!-- 原图片公告区域已删除 -->

    <div class="grid grid-cols-1 md:grid-cols-12 gap-4 mb-6">
        <div class="md:col-span-3 bg-white p-3 rounded-lg shadow-sm border border-slate-200 input-group">
            <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">Admin Name</label>
            <div class="flex items-center gap-2">
                <i class="fa-solid fa-user-shield text-slate-300"></i>
                <input type="text" id="adminName" placeholder="输入管理员ID" class="w-full text-sm outline-none font-medium text-slate-700">
            </div>
        </div>
        <div class="md:col-span-2 bg-white p-3 rounded-lg shadow-sm border border-slate-200 input-group">
            <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">Target ID</label>
            <div class="flex items-center gap-2">
                <i class="fa-solid fa-hashtag text-slate-300"></i>
                <input type="text" id="targetID" placeholder="ID/Name" class="w-full text-sm outline-none font-mono font-bold text-blue-600">
            </div>
        </div>
        <div class="md:col-span-7 bg-white p-3 rounded-lg shadow-sm border border-slate-200 flex flex-wrap gap-3 items-center">
            <div class="flex-1 min-w-[150px]">
                <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1 flex items-center justify-between">
                    预设理由
                    <button onclick="app.manageReasons()" class="text-[9px] text-blue-500 hover:text-blue-700" title="管理自定义理由"><i class="fa-regular fa-pen-to-square"></i> 管理</button>
                </label>
                <select id="reasonSelect" class="w-full text-sm bg-transparent outline-none cursor-pointer"></select>
            </div>
            <div class="w-px h-8 bg-slate-100 hidden sm:block"></div>
            <div class="flex-[2] min-w-[200px]">
                <label class="block text-[10px] font-bold text-slate-400 uppercase tracking-wider mb-1">最终理由</label>
                <input type="text" id="reasonText" placeholder="填写具体原因..." class="w-full text-sm outline-none text-slate-700">
            </div>
        </div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <!-- 左侧：地图层级生成 (内容不变) -->
        <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
            <div class="bg-slate-50 px-4 py-3 border-b border-slate-200 flex flex-wrap justify-between items-center gap-2">
                <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-map"></i> 地图层级生成</h2>
                <input type="text" id="mapSearch" placeholder="搜索地图..." class="text-xs p-1 px-2 rounded border focus:border-blue-500 outline-none w-40">
            </div>
            <div class="p-5 space-y-4">
                <div class="grid grid-cols-12 gap-2">
                    <div class="col-span-5">
                        <label class="text-[10px] text-slate-400 font-bold uppercase">地图</label>
                        <select id="mapSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition"></select>
                    </div>
                    <div class="col-span-4">
                        <label class="text-[10px] text-slate-400 font-bold uppercase">模式</label>
                        <select id="modeSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition"></select>
                    </div>
                    <div class="col-span-3">
                        <label class="text-[10px] text-slate-400 font-bold uppercase">层版本</label>
                        <select id="verSelect" class="w-full p-2 text-sm border rounded bg-white hover:border-blue-400 transition"></select>
                    </div>
                </div>
                <div class="bg-slate-50 rounded-lg p-3 border border-slate-100 relative">
                    <div class="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 text-slate-300 text-xs font-bold z-0">VS</div>
                    <div class="grid grid-cols-2 gap-6 relative z-10">
                        <div class="space-y-1">
                            <div class="text-[10px] font-bold text-blue-600 uppercase flex items-center gap-1">
                                <div class="w-2 h-2 rounded-full bg-blue-500"></div> 阵营 1
                            </div>
                            <select id="facA" class="w-full p-1.5 text-xs border border-blue-100 rounded focus:border-blue-500 outline-none"></select>
                            <select id="kitA" class="w-full p-1.5 text-xs border border-blue-100 rounded focus:border-blue-500 outline-none text-slate-500"></select>
                        </div>
                        <div class="space-y-1">
                            <div class="text-[10px] font-bold text-red-600 uppercase flex items-center gap-1 justify-end">
                                阵营 2 <div class="w-2 h-2 rounded-full bg-red-500"></div>
                            </div>
                            <select id="facB" class="w-full p-1.5 text-xs border border-red-100 rounded focus:border-red-500 outline-none text-right"></select>
                            <select id="kitB" class="w-full p-1.5 text-xs border border-red-100 rounded focus:border-red-500 outline-none text-slate-500 text-right"></select>
                        </div>
                    </div>
                </div>
                <div class="space-y-3 pt-2">
                    <div class="cursor-pointer group" onclick="app.copyCmdById('cmdChangeLayer')">
                        <div class="flex justify-between text-xs mb-1">
                            <span class="font-bold text-slate-500">立即切图 (ChangeLayer)</span>
                            <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                        </div>
                        <div class="bg-slate-800 font-mono text-xs p-3 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="cmdChangeLayer"></div>
                    </div>
                    <div class="cursor-pointer group" onclick="app.copyCmdById('cmdNextLayer')">
                        <div class="flex justify-between text-xs mb-1">
                            <span class="font-bold text-slate-500">设置下一张 (SetNextLayer)</span>
                            <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                        </div>
                        <div class="bg-slate-800 font-mono text-xs p-3 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="cmdNextLayer"></div>
                    </div>
                </div>
                <div class="flex gap-2 border-t pt-3 mt-2">
                    <button onclick="app.copyText('AdminEndMatch')" class="flex-1 py-2 bg-red-50 text-red-600 text-xs font-bold rounded border border-red-100 hover:bg-red-100 btn-action">结束本局</button>
                    <button onclick="app.copyText('AdminRestartMatch')" class="flex-1 py-2 bg-orange-50 text-orange-600 text-xs font-bold rounded border border-orange-100 hover:bg-orange-100 btn-action">重开本局</button>
                </div>
            </div>
        </div>

        <!-- 右侧：玩家处分及其他 (内容不变) -->
        <div class="space-y-6">
            <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                <div class="bg-slate-50 px-4 py-3 border-b border-slate-200">
                    <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-gavel"></i> 玩家处分</h2>
                </div>
                <div class="p-4 space-y-3">
                    <div class="p-2 border rounded hover:border-blue-400 cursor-pointer transition group bg-white" onclick="app.copyCmdById('kickCmd')">
                        <div class="flex justify-between items-center mb-1">
                            <span class="text-xs font-bold text-slate-500">Kick (踢出)</span>
                            <i class="fa-regular fa-copy text-slate-300 group-hover:text-blue-500"></i>
                        </div>
                        <code id="kickCmd" class="text-xs text-slate-800 font-mono block break-all cmd-display"></code>
                    </div>
                    <div class="p-2 border rounded hover:border-red-400 cursor-pointer transition group bg-white relative" onclick="app.copyCmdById('banCmd')">
                        <div class="flex justify-between items-center mb-1">
                            <span class="text-xs font-bold text-slate-500">Ban (封禁)</span>
                            <select id="banTime" class="text-[10px] border rounded bg-slate-50 px-1 py-0.5 outline-none cursor-pointer hover:bg-white" onclick="event.stopPropagation()"></select>
                        </div>
                        <code id="banCmd" class="text-xs text-red-600 font-mono block break-all cmd-display"></code>
                    </div>
                    <div class="p-2 border rounded hover:border-yellow-400 cursor-pointer transition group bg-white" onclick="app.copyCmdById('warnCmd')">
                        <div class="flex justify-between items-center mb-1">
                            <span class="text-xs font-bold text-slate-500">Warn (警告)</span>
                            <i class="fa-regular fa-copy text-slate-300 group-hover:text-yellow-500"></i>
                        </div>
                        <code id="warnCmd" class="text-xs text-yellow-600 font-mono block break-all cmd-display"></code>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                <div class="bg-slate-50 px-4 py-3 border-b border-slate-200">
                    <h2 class="font-bold text-slate-700 flex items-center gap-2"><i class="fa-solid fa-users-gear"></i> 队伍与小队 · 广播 · 传送</h2>
                </div>
                <div class="p-4">
                    <div class="flex gap-2 mb-3">
                        <input type="number" id="teamId" placeholder="阵营 ID (1/2)" class="w-1/2 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                        <input type="number" id="squadId" placeholder="小队 ID" class="w-1/2 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                    </div>
                    <div class="grid grid-cols-2 gap-2">
                        <button onclick="app.quickCmd('AdminForceTeamChangebyid', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                            <i class="fa-solid fa-right-left mr-1"></i> 强切阵营
                        </button>
                        <button onclick="app.quickCmd('AdminRemovePlayerFromSquadbyid', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                            <i class="fa-solid fa-user-xmark mr-1"></i> 踢出小队
                        </button>
                        <button onclick="app.quickCmd('AdminDemoteCommanderbyid', 'targetID')" class="p-2 bg-indigo-50 text-indigo-700 text-xs font-bold rounded hover:bg-indigo-100 text-left btn-action">
                            <i class="fa-solid fa-user-slash mr-1"></i> 卸任指挥
                        </button>
                        <button onclick="app.getDisbandCmd()" class="p-2 bg-red-50 text-red-700 text-xs font-bold rounded hover:bg-red-100 text-left btn-action">
                            <i class="fa-solid fa-ban mr-1"></i> 解散小队
                        </button>
                    </div>
                    <div class="mt-3 pt-3 border-t border-slate-100 grid grid-cols-3 gap-2">
                        <button onclick="app.copyText('AdminDisableVehicleClaiming 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消载具认领</button>
                        <button onclick="app.copyText('AdminNoRespawnTimer 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">取消复活时间</button>
                        <button onclick="app.copyText('AdminForceAllRoleAvailability 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">解除装备限制</button>
                        <button onclick="app.copyText('AdminDisableVehicleClaiming 0')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">解锁载具</button>
                        <button onclick="app.copyText('AdminAllKitsAvailable 1')" class="p-1.5 text-slate-500 text-[10px] bg-slate-100 rounded hover:bg-slate-200">解锁兵种</button>
                    </div>

                    <div class="mt-3 pt-3 border-t border-slate-100">
                        <h3 class="text-xs font-bold text-slate-500 mb-2"><i class="fa-solid fa-bullhorn mr-1"></i>广播消息</h3>
                        <div class="flex gap-2 mb-2">
                            <input type="text" id="broadcastMsg" placeholder="输入广播内容..." class="flex-1 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                        </div>
                        <div class="cursor-pointer group" onclick="app.copyCmdById('broadcastCmd')">
                            <div class="flex justify-between text-xs mb-1">
                                <span class="font-bold text-slate-500">AdminBroadcast</span>
                                <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                            </div>
                            <div class="bg-slate-800 font-mono text-xs p-2 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="broadcastCmd"></div>
                        </div>
                    </div>

                    <div class="mt-3">
                        <h3 class="text-xs font-bold text-slate-500 mb-2"><i class="fa-solid fa-rocket mr-1"></i>传送玩家 (Teleport)</h3>
                        <div class="flex gap-2 mb-2">
                            <input type="text" id="teleportTarget" placeholder="输入目标玩家名称..." class="flex-1 p-2 text-xs border rounded focus:border-indigo-500 outline-none">
                        </div>
                        <div class="cursor-pointer group" onclick="app.copyCmdById('teleportCmd')">
                            <div class="flex justify-between text-xs mb-1">
                                <span class="font-bold text-slate-500">AdminTeleportToPlayer</span>
                                <span class="text-blue-500 opacity-0 group-hover:opacity-100 transition-opacity"><i class="fa-regular fa-copy"></i> 点击复制</span>
                            </div>
                            <div class="bg-slate-800 font-mono text-xs p-2 rounded shadow-inner break-all hover:bg-slate-700 transition cmd-display" id="teleportCmd"></div>
                        </div>
                    </div>

                    <div class="mt-4 pt-2 border-t border-slate-200">
                        <div class="flex items-center gap-2 mb-1">
                            <i class="fa-regular fa-clock text-slate-400 text-xs"></i>
                            <span class="text-[10px] font-bold text-slate-400 uppercase">最近指令</span>
                            <button onclick="app.clearHistory()" class="text-[9px] text-slate-400 hover:text-red-400 ml-auto">清空</button>
                        </div>
                        <div id="historyContainer" class="flex flex-wrap gap-1 min-h-[28px]"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="toast" class="fixed top-5 left-1/2 -translate-x-1/2 bg-slate-800 text-white px-4 py-2 rounded-full shadow-lg transform transition-all duration-300 translate-y-[-150%] opacity-0 flex items-center gap-2 z-50">
    <i class="fa-solid fa-check-circle text-green-400"></i>
    <span class="text-sm font-medium" id="toastMessage">指令已复制</span>
</div>

<script>
(function() {
    'use strict';
    // ---------- 配置数据 ----------
    const CONFIG = {
        maps: [
            { label: "巴士拉", value: "AlBasrah" }, { label: "铁砧", value: "Anvil" },
            { label: "贝拉亚山口", value: "BelayaPass" }, { label: "黑色海岸", value: "BlackCoast" },
            { label: "乔拉", value: "Chora" }, { label: "费卢杰", value: "Fallujah" },
            { label: "愚者之路", value: "FoolsRoad" }, { label: "鹅湾", value: "GooseBay" },
            { label: "格罗多克", value: "Gorodok" }, { label: "哈留", value: "Harju" },
            { label: "詹森训练场", value: "JensensRange" }, { label: "卡玛德仕高地", value: "KamdeshHighlands" },
            { label: "科哈特", value: "KohatToi" }, { label: "寇坎", value: "Kokan" },
            { label: "拉什喀河谷", value: "LashkarValley" }, { label: "珞珈山谷", value: "LogarValley" },
            { label: "曼尼古根", value: "Manicouagan" }, { label: "梅斯蒂亚", value: "Mestia" },
            { label: "穆塔哈", value: "Mutaha" }, { label: "纳尔瓦", value: "Nara" },
            { label: "太平洋训练场", value: "PacificProvingGrounds" }, { label: "三贤群岛", value: "Sanxian" },
            { label: "斯科普", value: "Skorpo" }, { label: "苏玛瑞", value: "Sumari" },
            { label: "塔利尔郊区", value: "TalilOutskirts" }, { label: "叶霍里夫卡", value: "Yehorivka" }
        ],
        modes: [
            { label: "遭遇战", value: "Skirmish" }, { label: "随机攻防", value: "RAAS" },
            { label: "侵攻", value: "Invasion" }, { label: "叛乱", value: "Insurgency" },
            { label: "攻守有序", value: "AAS" }, { label: "摧毁", value: "Destruction" },
            { label: "区域控制", value: "TC" }, { label: "暖服", value: "Seed" }
        ],
        factions: [
            { label: "人民解放军", value: "PLA" }, { label: "人民解放军陆军两栖旅", value: "PLAAGF" },
            { label: "人民解放军海军陆战队", value: "PLANMC" }, { label: "美国陆军", value: "USA" },
            { label: "美国海军陆战队", value: "USMC" }, { label: "俄罗斯陆军", value: "RGF" },
            { label: "俄罗斯空降军", value: "VDV" }, { label: "澳大利亚国防军", value: "ADF" },
            { label: "英国陆军", value: "BAF" }, { label: "非正规民兵", value: "CAF" },
            { label: "中东联军", value: "MEA" }, { label: "土耳其陆军", value: "TLF" },
            { label: "乌克兰陆军", value: "AFU" }, { label: "非正规民兵部队", value: "IMF" },
            { label: "叛乱分子", value: "INS" }, { label: "加拿大抵抗力量", value: "CRF" }
        ],
        kits: [
            { label: "合成化", value: "CombinedArms" }, { label: "空中突击", value: "AirAssault" },
            { label: "装甲", value: "Armored" }, { label: "轻步兵", value: "LightInfantry" },
            { label: "机械化", value: "Mechanized" }, { label: "摩托化", value: "Motorized" },
            { label: "勤务保障", value: "Support" }
        ],
        banTimes: [
            { label: "1天", value: "1d" }, { label: "3天", value: "3d" },
            { label: "1周", value: "1w" }, { label: "1月", value: "1M" }, { label: "永久", value: "0" }
        ],
        defaultReasons: ["外挂", "无麦带队", "毒瘤", "数据异常"]
    };

    // ---------- DOM 元素缓存 (已移除公告相关) ----------
    const el = {
        adminName: document.getElementById('adminName'),
        targetID: document.getElementById('targetID'),
        reasonSelect: document.getElementById('reasonSelect'),
        reasonText: document.getElementById('reasonText'),
        banTime: document.getElementById('banTime'),
        mapSelect: document.getElementById('mapSelect'),
        modeSelect: document.getElementById('modeSelect'),
        verSelect: document.getElementById('verSelect'),
        facA: document.getElementById('facA'),
        facB: document.getElementById('facB'),
        kitA: document.getElementById('kitA'),
        kitB: document.getElementById('kitB'),
        mapSearch: document.getElementById('mapSearch'),
        broadcastMsg: document.getElementById('broadcastMsg'),
        teleportTarget: document.getElementById('teleportTarget'),
        cmdChangeLayer: document.getElementById('cmdChangeLayer'),
        cmdNextLayer: document.getElementById('cmdNextLayer'),
        kickCmd: document.getElementById('kickCmd'),
        banCmd: document.getElementById('banCmd'),
        warnCmd: document.getElementById('warnCmd'),
        broadcastCmd: document.getElementById('broadcastCmd'),
        teleportCmd: document.getElementById('teleportCmd'),
        teamId: document.getElementById('teamId'),
        squadId: document.getElementById('squadId'),
        historyContainer: document.getElementById('historyContainer'),
        toast: document.getElementById('toast'),
        toastMessage: document.getElementById('toastMessage')
    };

    // 检查关键元素是否存在
    for (let key in el) {
        if (!el[key]) {
            console.error('Missing element:', key);
            document.body.innerHTML = '<div style="color:red; padding:20px;">页面加载错误：缺少元素 ' + key + '，请刷新或联系管理员。</div>';
            return;
        }
    }

    // ---------- 状态 ----------
    let customReasons = [];
    let cmdHistory = [];
    try {
        customReasons = JSON.parse(localStorage.getItem('squad_op_custom_reasons')) || [];
        cmdHistory = JSON.parse(localStorage.getItem('squad_op_history')) || [];
    } catch (e) {}

    // ---------- 工具函数 ----------
    function saveCustomReasons() {
        localStorage.setItem('squad_op_custom_reasons', JSON.stringify(customReasons));
        refreshReasonSelect();
    }
    function saveHistory() {
        localStorage.setItem('squad_op_history', JSON.stringify(cmdHistory));
        updateHistoryUI();
    }
    function populateSelect(selectEl, list, defValue) {
        selectEl.innerHTML = '';
        list.forEach(item => {
            const label = item.label || item;
            const value = item.value || item;
            const option = new Option(label, value);
            selectEl.add(option);
            if (defValue && (value === defValue || label === defValue)) {
                selectEl.value = value;
            }
        });
    }
    function refreshReasonSelect() {
        el.reasonSelect.innerHTML = '<option value="">-- 选择预设 --</option>';
        CONFIG.defaultReasons.forEach(r => el.reasonSelect.add(new Option(r, r)));
        customReasons.forEach(r => {
            if (!CONFIG.defaultReasons.includes(r)) el.reasonSelect.add(new Option(r, r));
        });
    }
    function escapeQuotes(str) {
        return str ? str.replace(/"/g, '\\\\"') : str;
    }
    function highlightCmd(cmdText) {
        if (!cmdText) return cmdText;
        const parts = cmdText.match(/^(\\S+)\\s+(.*)$/s);
        if (!parts) return '<span class="cmd-command">' + cmdText + '</span>';
        const command = parts[1];
        let rest = parts[2];
        rest = rest.replace(/"([^"]*)"/g, '<span class="cmd-string">"$1"</span>');
        return '<span class="cmd-command">' + command + '</span> ' + rest;
    }
    function debounce(func, wait) {
        let timeout;
        return function(...args) {
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(this, args), wait);
        };
    }

    // ---------- 渲染核心 ----------
    function renderAllCmds() {
        const admin = el.adminName.value.trim() || 'Admin';
        const id = el.targetID.value.trim() || '<ID>';
        const rawReason = el.reasonText.value.trim() || '违反服务器规则';
        const reason = escapeQuotes(rawReason);
        const banTime = el.banTime.value;
        const finalReason = reason + ' [处理人:' + admin + ']';

        const kickRaw = 'Adminkickbyid "' + escapeQuotes(id) + '" ' + finalReason;
        el.kickCmd.innerHTML = highlightCmd(kickRaw);
        el.kickCmd.setAttribute('data-raw', kickRaw);

        const banRaw = 'Adminbanbyid "' + escapeQuotes(id) + '" ' + banTime + ' ' + finalReason;
        el.banCmd.innerHTML = highlightCmd(banRaw);
        el.banCmd.setAttribute('data-raw', banRaw);

        const warnRaw = 'AdminWarn "' + escapeQuotes(id) + '" ' + reason;
        el.warnCmd.innerHTML = highlightCmd(warnRaw);
        el.warnCmd.setAttribute('data-raw', warnRaw);

        // 广播
        const msg = el.broadcastMsg.value.trim();
        const escapedMsg = escapeQuotes(msg);
        const broadcastRaw = escapedMsg ? 'AdminBroadcast "' + escapedMsg + '"' : 'AdminBroadcast "..."';
        el.broadcastCmd.innerHTML = highlightCmd(broadcastRaw);
        el.broadcastCmd.setAttribute('data-raw', broadcastRaw);

        // 传送
        const name = el.teleportTarget.value.trim();
        const escapedName = escapeQuotes(name);
        const teleportRaw = escapedName ? 'AdminTeleportToPlayer "' + escapedName + '"' : 'AdminTeleportToPlayer "..."';
        el.teleportCmd.innerHTML = highlightCmd(teleportRaw);
        el.teleportCmd.setAttribute('data-raw', teleportRaw);

        renderLayer();
    }

    function renderLayer() {
        const map = el.mapSelect.value;
        const mode = el.modeSelect.value;
        const ver = el.verSelect.value;
        const fa = el.facA.value;
        const ka = el.kitA.value;
        const fb = el.facB.value;
        const kb = el.kitB.value;
        const layerStr = map + '_' + mode + '_' + ver + ' ' + fa + '+' + ka + ' ' + fb + '+' + kb;
        const changeRaw = 'AdminChangeLayer ' + layerStr;
        const nextRaw = 'AdminSetNextLayer ' + layerStr;
        el.cmdChangeLayer.innerHTML = highlightCmd(changeRaw);
        el.cmdChangeLayer.setAttribute('data-raw', changeRaw);
        el.cmdNextLayer.innerHTML = highlightCmd(nextRaw);
        el.cmdNextLayer.setAttribute('data-raw', nextRaw);
    }

    // ---------- 事件绑定 ----------
    const debouncedRender = debounce(renderAllCmds, 250);
    el.adminName.addEventListener('input', () => {
        localStorage.setItem('squad_op_admin', el.adminName.value.trim());
        debouncedRender();
    });
    el.targetID.addEventListener('input', debouncedRender);
    el.reasonText.addEventListener('input', debouncedRender);
    el.broadcastMsg.addEventListener('input', debouncedRender);
    el.teleportTarget.addEventListener('input', debouncedRender);
    el.banTime.addEventListener('change', renderAllCmds);
    el.reasonSelect.addEventListener('change', (e) => {
        if (e.target.value) {
            el.reasonText.value = e.target.value;
            renderAllCmds();
        }
    });
    el.mapSearch.addEventListener('input', debounce(() => {
        const term = el.mapSearch.value.toLowerCase();
        const sel = el.mapSelect;
        sel.innerHTML = '';
        CONFIG.maps.forEach(map => {
            if (map.label.toLowerCase().includes(term) || map.value.toLowerCase().includes(term)) {
                sel.add(new Option(map.label, map.value));
            }
        });
        renderLayer();
    }, 300));
    ['mapSelect', 'modeSelect', 'verSelect', 'facA', 'kitA', 'facB', 'kitB'].forEach(id => {
        const e = document.getElementById(id);
        if (e) e.addEventListener('change', renderLayer);
    });

    // ---------- 复制等操作 ----------
    function copyText(text) {
        if (!text || text.includes('...')) {
            showToast('请先填写必要参数', 'warning');
            return;
        }
        if (text.includes('<ID>') || (text.includes('AdminWarn') && !el.targetID.value.trim())) {
            showToast('请填写目标ID', 'error');
            return;
        }
        navigator.clipboard.writeText(text).then(() => {
            if (!cmdHistory.includes(text)) {
                cmdHistory = [text, ...cmdHistory].slice(0, 10);
                saveHistory();
            }
            showToast('指令已复制');
        }).catch(() => {
            const ta = document.createElement('textarea');
            ta.value = text;
            document.body.appendChild(ta);
            ta.select();
            document.execCommand('copy');
            document.body.removeChild(ta);
            if (!cmdHistory.includes(text)) {
                cmdHistory = [text, ...cmdHistory].slice(0, 10);
                saveHistory();
            }
            showToast('指令已复制');
        });
    }

    function showToast(msg) {
        el.toastMessage.textContent = msg;
        el.toast.classList.remove('translate-y-[-150%]', 'opacity-0');
        el.toast.classList.add('translate-y-0', 'opacity-100');
        setTimeout(() => {
            el.toast.classList.remove('translate-y-0', 'opacity-100');
            el.toast.classList.add('translate-y-[-150%]', 'opacity-0');
        }, 2000);
    }

    function updateHistoryUI() {
        el.historyContainer.innerHTML = '';
        cmdHistory.forEach(cmd => {
            const span = document.createElement('span');
            span.className = 'history-item';
            span.textContent = cmd;
            span.onclick = () => copyText(cmd);
            el.historyContainer.appendChild(span);
        });
    }

    // ---------- 暴露全局方法 ----------
    window.app = {
        copyCmdById: (id) => copyText(document.getElementById(id)?.getAttribute('data-raw') || ''),
        copyText: copyText,
        quickCmd: (prefix, inputId) => {
            const val = el[inputId]?.value.trim();
            if (!val) { showToast('请输入目标ID', 'error'); return; }
            copyText(prefix + ' ' + val);
        },
        getDisbandCmd: () => {
            const t = el.teamId.value.trim();
            const s = el.squadId.value.trim();
            if (!t || !s) { showToast('请填写阵营ID和小队ID', 'error'); return; }
            copyText('AdminDisbandSquad ' + t + ' ' + s);
        },
        manageReasons: () => {
            const action = prompt('输入 "add:新理由" 添加, "remove:理由" 删除, 或 "clear" 清空自定义');
            if (!action) return;
            if (action === 'clear') {
                customReasons = [];
                saveCustomReasons();
            } else if (action.startsWith('add:')) {
                const newR = action.substring(4).trim();
                if (newR && !customReasons.includes(newR) && !CONFIG.defaultReasons.includes(newR)) {
                    customReasons.push(newR);
                    saveCustomReasons();
                }
            } else if (action.startsWith('remove:')) {
                const remR = action.substring(7).trim();
                const idx = customReasons.indexOf(remR);
                if (idx !== -1) {
                    customReasons.splice(idx, 1);
                    saveCustomReasons();
                }
            }
        },
        resetConfig: () => {
            if (confirm('确定重置所有设置？')) {
                localStorage.clear();
                location.reload();
            }
        },
        clearHistory: () => {
            cmdHistory = [];
            saveHistory();
        }
        // 已移除 updateAnnouncementImage
    };

    // ---------- 初始化 ----------
    // 填充下拉框
    populateSelect(el.mapSelect, CONFIG.maps, 'Mutaha');
    populateSelect(el.modeSelect, CONFIG.modes, 'RAAS');
    el.verSelect.innerHTML = '';
    for (let i = 1; i <= 3; i++) {
        el.verSelect.add(new Option('v' + i, 'v' + i));
    }
    populateSelect(el.facA, CONFIG.factions, 'USA');
    populateSelect(el.facB, CONFIG.factions, 'RGF');
    populateSelect(el.kitA, CONFIG.kits, 'CombinedArms');
    populateSelect(el.kitB, CONFIG.kits, 'CombinedArms');
    populateSelect(el.banTime, CONFIG.banTimes, '1d');
    refreshReasonSelect();

    // 加载本地存储
    const savedAdmin = localStorage.getItem('squad_op_admin');
    if (savedAdmin) el.adminName.value = savedAdmin;
    const savedReason = localStorage.getItem('squad_op_last_reason');
    if (savedReason) el.reasonText.value = savedReason;

    // 初始渲染
    renderAllCmds();
    updateHistoryUI();
})();
</script>
</body>
</html>`;

    return new Response(html, {
      headers: { "content-type": "text/html;charset=UTF-8" },
    });
  },
};
