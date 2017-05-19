onmessage = function (a) {
    log(a.data);
    switch (a.data.command) {
        case"init":
            Vad();
            break;
        case"appendData":
            AppendData(a.data.pcmData, a.data.nSamples);
            break
    }
};
function log(a) {
    postMessage({type: "debug", message: a})
}
var ESVadStatus = {
    "ESVAD_SILENCE": 0,
    "ESVAD_CHECK_BEGIN": 1,
    "ESVAD_ACTIVE": 2,
    "ESVAD_CHECK_END": 3,
    "ESVAD_INACTIVE": 4
};
var ESVAD_DISABLE = 0;
var ES_CMN_UPDATE_RATE = 46;
var ES_CMN_UPDATE_RATE_C0 = 12;
var ESR_PCMBUFFER_SIZE = 160 * 1024;
var ESR_FRAME_SIZE = 200;
var ESR_FRAME_STEP = 80;
var ESR_BACK_FRAMES = 129;
var ESR_MAX_RESULT = 16;
var RECORD_SAMPLESPERFRAME_DEF = 200;
var ESIL_CHKBG_FRAMENUM = 3;
var ELOW_CHKBG_FRAMENUM = 20;
var EHIGH_CHKBG_FRAMENUM = 20;
var EHIGH_CHKEND_FRAMENUM_SHORT = 200;
var EHIGH_CHKEND_FRAMENUM_LONG = 80;
var ELOW_CHKEND_FRAMENUM = 60;
var ESR_FRAME_MAXNUM = ESR_BACK_FRAMES;
var ESR_MFCCFRAME_MAXNUM = 9;
var MINIMUM_SPEECH_FRAMENUM = 18;
var ELOW_VALIDREQ_THRESH = 5;
var EHIGH_VALIDREQ_THRESH = 4;
var EHIGH_ENDVALID_THRESH = 3;
var ELOW_ENDVALID_THRESH = 10;
var SPEECH_BEGIN_MARGIN = 25;
var SPEECH_END_MARGIN = 40;
var ESR_MAX_EHIGH_COEFF = 256;
var TRANSFORM_PREEMCOEF_DEF = 31785;
var TRANSFORM_CHANSNUM_DEF = 24;
var TRANSFORM_CEPSNUM_DEF = 11;
var TRANSFORM_STEP_DEF = 1;
var FEATURE_DIMNESION = 36;
var TRANSFROM_CEPLIFTER_DEF = 22;
var TRANSFORM_FFTNUM_DEF = 512;
var TRANSFORM_HALFFFTNUM_DEF = 256;
var ESR_FEATURE_MEMSIZE = 2 * FEATURE_DIMNESION;
var ESR_SKIP_FRAME = 8;
var ESR_MATH_LN2 = 2907270;
var ESR_MATH_10LN2 = 29072700;
var g_s16SimpleLnTable = [0, 32, 64, 96, 128, 160, 191, 223, 255, 287, 318, 350, 382, 413, 445, 477, 508, 540, 571, 602, 634, 665, 697, 728, 759, 790, 822, 853, 884, 915, 946, 977, 1008, 1039, 1070, 1101, 1132, 1163, 1194, 1225, 1256, 1286, 1317, 1348, 1379, 1409, 1440, 1471, 1501, 1532, 1562, 1593, 1623, 1654, 1684, 1714, 1745, 1775, 1805, 1836, 1866, 1896, 1926, 1956, 1987, 2017, 2047, 2077, 2107, 2137, 2167, 2197, 2227, 2256, 2286, 2316, 2346, 2376, 2406, 2435, 2465, 2495, 2524, 2554, 2583, 2613, 2643, 2672, 2702, 2731, 2760, 2790, 2819, 2849, 2878, 2907, 2936, 2966, 2995, 3024, 3053, 3082, 3111, 3141, 3170, 3199, 3228, 3257, 3286, 3315, 3343, 3372, 3401, 3430, 3459, 3488, 3516, 3545, 3574, 3603, 3631, 3660, 3688, 3717, 3746, 3774, 3803, 3831, 3860, 3888, 3916, 3945, 3973, 4001, 4030, 4058, 4086, 4115, 4143, 4171, 4199, 4227, 4255, 4283, 4311, 4340, 4368, 4396, 4424, 4451, 4479, 4507, 4535, 4563, 4591, 4619, 4646, 4674, 4702, 4730, 4757, 4785, 4813, 4840, 4868, 4895, 4923, 4950, 4978, 5005, 5033, 5060, 5088, 5115, 5143, 5170, 5197, 5224, 5252, 5279, 5306, 5333, 5361, 5388, 5415, 5442, 5469, 5496, 5523, 5550, 5577, 5604, 5631, 5658, 5685, 5712, 5739, 5766, 5792, 5819, 5846, 5873, 5900, 5926, 5953, 5980, 6006, 6033, 6060, 6086, 6113, 6139, 6166, 6192, 6219, 6245, 6272, 6298, 6324, 6351, 6377, 6403, 6430, 6456, 6482, 6509, 6535, 6561, 6587, 6613, 6640, 6666, 6692, 6718, 6744, 6770, 6796, 6822, 6848, 6874, 6900, 6926, 6952, 6977, 7003, 7029, 7055, 7081, 7107, 7132, 7158, 7184, 7209, 7235, 7261, 7286, 7312, 7338, 7363, 7389, 7414, 7440, 7465, 7491, 7516, 7542, 7567, 7592, 7618, 7643, 7668, 7694, 7719, 7744, 7770, 7795, 7820, 7845, 7870, 7896, 7921, 7946, 7971, 7996, 8021, 8046, 8071, 8096, 8121, 8146, 8171, 8196, 8221, 8246, 8271, 8295, 8320, 8345, 8370, 8395, 8419, 8444, 8469, 8494, 8518, 8543, 8568, 8592, 8617, 8641, 8666, 8691, 8715, 8740, 8764, 8789, 8813, 8837, 8862, 8886, 8911, 8935, 8959, 8984, 9008, 9032, 9057, 9081, 9105, 9129, 9154, 9178, 9202, 9226, 9250, 9274, 9299, 9323, 9347, 9371, 9395, 9419, 9443, 9467, 9491, 9515, 9539, 9562, 9586, 9610, 9634, 9658, 9682, 9706, 9729, 9753, 9777, 9801, 9824, 9848, 9872, 9895, 9919, 9943, 9966, 9990, 10013, 10037, 10061, 10084, 10108, 10131, 10155, 10178, 10202, 10225, 10248, 10272, 10295, 10319, 10342, 10365, 10389, 10412, 10435, 10458, 10482, 10505, 10528, 10551, 10574, 10598, 10621, 10644, 10667, 10690, 10713, 10736, 10759, 10782, 10805, 10828, 10851, 10874, 10897, 10920, 10943, 10966, 10989, 11012, 11035, 11058, 11080, 11103, 11126, 11149, 11171, 11194, 11217, 11240, 11262, 11285, 11308, 11330, 11353, 11376, 11398, 11421, 11443, 11466, 11489, 11511, 11534, 11556, 11579, 11601, 11623, 11646, 11668, 11691, 11713, 11735, 11758, 11780, 11803, 11825, 11847, 11869, 11892, 11914, 11936, 11958, 11981, 12003, 12025, 12047, 12069, 12091, 12114, 12136, 12158, 12180, 12202, 12224, 12246, 12268, 12290, 12312, 12334, 12356, 12378, 12400, 12422, 12444, 12465, 12487, 12509, 12531, 12553, 12575, 12596, 12618, 12640, 12662, 12683, 12705, 12727, 12749, 12770, 12792, 12814, 12835, 12857, 12878, 12900, 12922, 12943, 12965, 12986, 13008, 13029, 13051, 13072, 13094, 13115, 13137, 13158, 13179, 13201, 13222, 13244, 13265, 13286, 13308, 13329, 13350, 13372, 13393, 13414, 13435, 13457, 13478, 13499, 13520, 13541, 13562, 13584, 13605, 13626, 13647, 13668, 13689, 13710, 13731, 13752, 13773, 13794, 13815, 13836, 13857, 13878, 13899, 13920, 13941, 13962, 13983, 14004, 14025, 14045, 14066, 14087, 14108, 14129, 14149, 14170, 14191, 14212, 14232, 14253, 14274, 14295, 14315, 14336, 14357, 14377, 14398, 14418, 14439, 14460, 14480, 14501, 14521, 14542, 14562, 14583, 14603, 14624, 14644, 14665, 14685, 14706, 14726, 14747, 14767, 14787, 14808, 14828, 14848, 14869, 14889, 14909, 14930, 14950, 14970, 14991, 15011, 15031, 15051, 15071, 15092, 15112, 15132, 15152, 15172, 15192, 15213, 15233, 15253, 15273, 15293, 15313, 15333, 15353, 15373, 15393, 15413, 15433, 15453, 15473, 15493, 15513, 15533, 15553, 15573, 15593, 15612, 15632, 15652, 15672, 15692, 15712, 15731, 15751, 15771, 15791, 15811, 15830, 15850, 15870, 15889, 15909, 15929, 15948, 15968, 15988, 16007, 16027, 16047, 16066, 16086, 16105, 16125, 16145, 16164, 16184, 16203, 16223, 16242, 16262, 16281, 16301, 16320, 16340, 16359, 16378, 16398, 16417, 16437, 16456, 16475, 16495, 16514, 16533, 16553, 16572, 16591, 16610, 16630, 16649, 16668, 16687, 16707, 16726, 16745, 16764, 16784, 16803, 16822, 16841, 16860, 16879, 16898, 16917, 16937, 16956, 16975, 16994, 17013, 17032, 17051, 17070, 17089, 17108, 17127, 17146, 17165, 17184, 17203, 17222, 17240, 17259, 17278, 17297, 17316, 17335, 17354, 17373, 17391, 17410, 17429, 17448, 17467, 17485, 17504, 17523, 17542, 17560, 17579, 17598, 17616, 17635, 17654, 17673, 17691, 17710, 17728, 17747, 17766, 17784, 17803, 17821, 17840, 17859, 17877, 17896, 17914, 17933, 17951, 17970, 17988, 18007, 18025, 18044, 18062, 18080, 18099, 18117, 18136, 18154, 18173, 18191, 18209, 18228, 18246, 18264, 18283, 18301, 18319, 18337, 18356, 18374, 18392, 18411, 18429, 18447, 18465, 18483, 18502, 18520, 18538, 18556, 18574, 18592, 18611, 18629, 18647, 18665, 18683, 18701, 18719, 18737, 18755, 18773, 18791, 18810, 18828, 18846, 18864, 18882, 18900, 18917, 18935, 18953, 18971, 18989, 19007, 19025, 19043, 19061, 19079, 19097, 19114, 19132, 19150, 19168, 19186, 19204, 19221, 19239, 19257, 19275, 19293, 19310, 19328, 19346, 19364, 19381, 19399, 19417, 19434, 19452, 19470, 19487, 19505, 19523, 19540, 19558, 19576, 19593, 19611, 19628, 19646, 19663, 19681, 19699, 19716, 19734, 19751, 19769, 19786, 19804, 19821, 19839, 19856, 19873, 19891, 19908, 19926, 19943, 19961, 19978, 19995, 20013, 20030, 20048, 20065, 20082, 20100, 20117, 20134, 20151, 20169, 20186, 20203, 20221, 20238, 20255, 20272, 20290, 20307, 20324, 20341, 20358, 20376, 20393, 20410, 20427, 20444, 20461, 20479, 20496, 20513, 20530, 20547, 20564, 20581, 20598, 20615, 20632, 20649, 20666, 20683, 20700, 20717, 20734, 20751, 20768, 20785, 20802, 20819, 20836, 20853, 20870, 20887, 20904, 20921, 20938, 20955, 20972, 20988, 21005, 21022, 21039, 21056, 21073, 21089, 21106, 21123, 21140, 21157, 21173, 21190, 21207, 21224, 21240, 21257, 21274, 21291, 21307, 21324, 21341, 21357, 21374, 21391, 21407, 21424, 21441, 21457, 21474, 21491, 21507, 21524, 21540, 21557, 21573, 21590, 21607, 21623, 21640, 21656, 21673, 21689, 21706, 21722, 21739, 21755, 21772, 21788, 21805, 21821, 21837, 21854, 21870, 21887, 21903, 21920, 21936, 21952, 21969, 21985, 22001, 22018, 22034, 22050, 22067, 22083, 22099, 22116, 22132, 22148, 22164, 22181, 22197, 22213, 22229, 22246, 22262, 22278, 22294, 22311, 22327, 22343, 22359, 22375, 22391, 22408, 22424, 22440, 22456, 22472, 22488, 22504, 22520, 22537, 22553, 22569, 22585, 22601, 22617, 22633, 22649, 22665, 22681, 22697];
var m_iVADState;
var m_s32ESil;
var m_s32ELow;
var m_s32EHigh;
var m_s32EMax;
var m_latestVolume;
var m_pPCMBuffer;
var m_iPCMStart;
var m_iPCMEnd;
var m_pPCMFrame;
var m_ppPCM;
var m_iFrameEnd;
var m_pFrameEnergy;
var m_iFrameHead;
var m_iFrameCheck;
var m_iSpeechBegin;
var m_iSpeechEnd;
var m_iSpeechEnd2;
var m_iFrameCurrent;
var enableVoiceDataCache = true;
var outAudioList = new Array();
var m_nCheckEndFrame;
var speechStart = false;
var speechEnd = false;
function VadState() {
    return m_iVADState
}
function Volume() {
    return m_latestVolume
}
function SpeechStart() {
    return speechStart
}
function SpeechEnd() {
    return speechEnd
}
function Vad() {
    log("vad init");
    m_pPCMBuffer = new Array();
    m_pPCMFrame = new Array();
    m_pFrameEnergy = new Array();
    m_ppPCM = new Array();
    Reset()
}
function Reset() {
    m_iFrameEnd = 0;
    m_iFrameHead = 0;
    m_iSpeechBegin = 0;
    m_s32ESil = 0;
    m_iVADState = ESVadStatus.ESVAD_SILENCE;
    m_iFrameCurrent = parseInt(ESR_SKIP_FRAME);
    m_iSpeechEnd = 0;
    m_iPCMEnd = 0;
    m_iPCMStart = 0
}
function BlockCopy(f, a, g, d, e) {
    var c = 0;
    var b = 0;
    while (c < e) {
        m_ppPCM[d + c] = f[a + c];
        c++
    }
}
function AppendData(c, f) {
    var h;
    var e;
    log("call AppendData function, pcmDataLength : " + f + ", pcmData[0] : " + c[0]);
    if (1 == f) {
        var k = m_iPCMEnd;
        m_pPCMBuffer[k] = c[0];
        ++k;
        if (k >= ESR_PCMBUFFER_SIZE) {
            k -= ESR_PCMBUFFER_SIZE
        }
        if (k == m_iPCMStart) {
            return 7
        }
        m_iPCMEnd = k;
        return 0
    }
    h = parseInt(m_iPCMEnd - m_iPCMStart);
    if (h < 0) {
        h += ESR_PCMBUFFER_SIZE
    }
    h += f;
    if (h > ESR_PCMBUFFER_SIZE - 1) {
        return 7
    }
    if (m_iPCMEnd + f < ESR_PCMBUFFER_SIZE) {
        for (e = 0; e < f; e++) {
            m_pPCMBuffer[m_iPCMEnd + e] = c[e]
        }
        m_iPCMEnd += f
    } else {
        var b = ESR_PCMBUFFER_SIZE - m_iPCMEnd;
        b = ESR_PCMBUFFER_SIZE - m_iPCMEnd;
        for (e = 0; e < b; e++) {
            m_pPCMBuffer[m_iPCMEnd + e] = c[e]
        }
        for (e = 0; e < f - b; e++) {
            m_pPCMBuffer[e] = c[b + e]
        }
        m_iPCMEnd = f - b
    }
    log("call AppendData function, m_iPCMEnd : " + m_iPCMEnd);
    while (true) {
        var g = false;
        if (ESVadStatus.ESVAD_INACTIVE != m_iVADState) {
            g = GetOneFrame();
            if (g) {
                m_pFrameEnergy[m_iFrameEnd % ESR_FRAME_MAXNUM] = CalcFrameEnergy();
                BlockCopy(m_pPCMFrame, 0, m_ppPCM, (m_iFrameEnd % parseInt(ESR_FRAME_MAXNUM)) * parseInt(ESR_FRAME_STEP) * 2, parseInt(ESR_FRAME_STEP) * 2);
                m_iFrameEnd++;
                if (m_iFrameEnd < parseInt(ESIL_CHKBG_FRAMENUM)) {
                    continue
                }
                CheckVoice()
            }
        }
        if (m_iFrameCurrent < m_iSpeechEnd) {
            if (enableVoiceDataCache) {
                var d = new Array();
                BlockCopy(m_ppPCM, (m_iFrameCurrent % parseInt(ESR_FRAME_MAXNUM)) * parseInt(ESR_FRAME_STEP) * 2, d, 0, parseInt(ESR_FRAME_STEP * 2));
                outAudioList[outAudioList.length] = d
            }
            m_iFrameCurrent++
        }
        if (ESVadStatus.ESVAD_INACTIVE == m_iVADState) {
            if (m_iFrameCurrent < m_iSpeechEnd) {
                if (enableVoiceDataCache) {
                    var d = new Array();
                    BlockCopy(m_ppPCM, (m_iFrameCurrent % parseInt(ESR_FRAME_MAXNUM)) * parseInt(ESR_FRAME_STEP) * 2, d, 0, parseInt(ESR_FRAME_STEP * 2));
                    outAudioList[outAudioList.length] = d
                }
                m_iFrameCurrent++
            }
        }
        if (!g) {
            break
        }
    }
    m_latestVolume = 0;
    var a = 0;
    for (e = 0; e < f; e++) {
        var j;
        j = (c[e] >> 2);
        a += (j * j + 8) >> 4
    }
    a /= f;
    if (a < 256) {
        m_latestVolume = 0
    } else {
        m_latestVolume = simple_table_ln(a, 6) >> 22;
        if (m_latestVolume > 9) {
            m_latestVolume = 9
        }
    }
    log("vad volume : " + m_latestVolume);
    postMessage({command: "volume", message: m_latestVolume});
    return 0
}
function GetVoiceData() {
    var a = null;
    if (outAudioList.Count > 0) {
        a = outAudioList[0];
        outAudioList.splice(0, 1)
    }
    return a
}
function GetVoiceDataSamples() {
    return outAudioList.Count * parseInt(ESR_FRAME_STEP)
}
function simple_table_ln(e, b) {
    var d;
    var c;
    var a = b;
    ++e;
    if ((e & 4294901760) == 0) {
        e <<= 16;
        a += 16
    }
    if ((e & 4278190080) == 0) {
        e <<= 8;
        a += 8
    }
    if ((e & 4026531840) == 0) {
        e <<= 4;
        a += 4
    }
    if ((e & 3221225472) == 0) {
        e <<= 2;
        a += 2
    }
    if ((e & 2147483648) == 0) {
        e <<= 1;
        a += 1
    }
    e = e - 2147483648;
    c = (e >> 21);
    d = (g_s16SimpleLnTable[c] << 7);
    d += (31 - a) * parseInt(ESR_MATH_LN2);
    return d
}
function CalcFrameEnergy() {
    var a = 0;
    var c;
    var d;
    for (c = 0; c < ESR_FRAME_SIZE; c++) {
        a += m_pPCMFrame[c]
    }
    a = a / parseInt(ESR_FRAME_SIZE);
    d = 0;
    for (c = 0; c < ESR_FRAME_SIZE; c++) {
        var b, e;
        b = m_pPCMFrame[c];
        e = b - a;
        d += (e * e + 128) >> 8
    }
    d >>= 2;
    return Math.max(40, (d))
}
function GetOneFrame() {
    var b = parseInt(m_iPCMEnd - m_iPCMStart);
    if (b < 0) {
        b += parseInt(ESR_PCMBUFFER_SIZE)
    }
    if (b < ESR_FRAME_SIZE) {
        return false
    }
    if (m_iPCMStart + ESR_FRAME_SIZE <= ESR_PCMBUFFER_SIZE) {
        var a = 0;
        for (; a < ESR_FRAME_SIZE; a++) {
            m_pPCMFrame[a] = m_pPCMBuffer[m_iPCMStart + a]
        }
        m_iPCMStart += ESR_FRAME_STEP
    } else {
        var c = ESR_PCMBUFFER_SIZE - m_iPCMStart;
        var a;
        for (a = 0; a < c; a++) {
            m_pPCMFrame[a] = m_pPCMBuffer[m_iPCMStart + a]
        }
        for (a = 0; a < ESR_FRAME_SIZE - c; a++) {
            m_pPCMFrame[c + a] = m_pPCMBuffer[a]
        }
        m_iPCMStart += ESR_FRAME_STEP;
        if (m_iPCMStart > ESR_PCMBUFFER_SIZE) {
            m_iPCMStart -= ESR_PCMBUFFER_SIZE
        }
    }
    return true
}
function CheckEngery(b, e, a) {
    var d = 0;
    var c = 0;
    for (; d < a; d++) {
        if (m_pFrameEnergy[(m_iFrameCheck + d) % ESR_FRAME_MAXNUM] > b) {
            c++
        } else {
            c = 0
        }
        if (c > e) {
            m_iFrameCheck = d + m_iFrameCheck - e;
            return true
        }
    }
    return false
}
function CheckVoice() {
    var b, d, e, c;
    var g, f;
    b = m_iFrameEnd - m_iFrameHead;
    while (b != 0) {
        b = m_iFrameEnd - m_iFrameHead;
        if (b == 0) {
            return
        }
        if (0 == m_s32ESil) {
            if (b < ESIL_CHKBG_FRAMENUM) {
                return
            }
            if (m_iFrameHead <= ESR_FRAME_SIZE / ESR_FRAME_STEP) {
                ++m_iFrameHead;
                continue
            }
            m_nCheckEndFrame = parseInt(EHIGH_CHKEND_FRAMENUM_SHORT);
            m_s32ESil = 0;
            for (e = 0; e < ESIL_CHKBG_FRAMENUM; e++) {
                m_s32ESil += m_pFrameEnergy[(m_iFrameHead + e) % ESR_FRAME_MAXNUM]
            }
            m_s32ESil /= parseInt(ESIL_CHKBG_FRAMENUM);
            m_iFrameCheck = m_iFrameHead + 1;
            g = m_s32ESil + 200;
            m_s32ELow = parseInt(g * 20 / (((simple_table_ln(g, 0) + ESR_MATH_10LN2) >> 18) - (4 << 4)));
            m_s32ELow <<= 5;
            m_s32ELow -= 200
        }
        log("check vad state : " + m_iVADState);
        switch (m_iVADState) {
            case ESVadStatus.ESVAD_SILENCE:
                d = parseInt(m_iFrameEnd - m_iFrameCheck);
                if (d < ELOW_CHKBG_FRAMENUM) {
                    return
                }
                if (CheckEngery(m_s32ELow, parseInt(ELOW_VALIDREQ_THRESH), parseInt(ELOW_CHKBG_FRAMENUM))) {
                    for (e = m_iFrameHead + 1; e <= m_iFrameCheck - ESIL_CHKBG_FRAMENUM; ++e) {
                        g = 0;
                        for (c = 0; c < ESIL_CHKBG_FRAMENUM; c++) {
                            g += m_pFrameEnergy[(e + c) % ESR_FRAME_MAXNUM]
                        }
                        g /= parseInt(ESIL_CHKBG_FRAMENUM);
                        if (g < m_s32ESil) {
                            m_s32ESil = g;
                            m_iFrameHead = e
                        }
                    }
                    g = parseInt((simple_table_ln(m_s32ESil, 0) + ESR_MATH_10LN2) >> 14);
                    f = ((g - (9 << 8)) * (g - (9 << 8))) >> 12;
                    f += (32 << 4);
                    m_s32EHigh = (m_s32ESil * (720 / 2) / f) << 5;
                    m_iSpeechBegin = m_iFrameCheck;
                    m_iVADState = ESVadStatus.ESVAD_CHECK_BEGIN;
                    speechStart = true
                } else {
                    m_s32ESil = 0;
                    m_iVADState = ESVadStatus.ESVAD_SILENCE;
                    m_iFrameHead++
                }
                break;
            case ESVadStatus.ESVAD_CHECK_BEGIN:
                d = parseInt(m_iFrameEnd - m_iFrameCheck);
                if (d < EHIGH_CHKBG_FRAMENUM) {
                    return
                }
                if (CheckEngery(m_s32EHigh, parseInt(EHIGH_VALIDREQ_THRESH), parseInt(EHIGH_CHKBG_FRAMENUM))) {
                    var a;
                    m_iFrameHead = m_iSpeechBegin;
                    m_iFrameCheck = m_iFrameHead + 1;
                    m_iFrameCurrent = Math.max(m_iSpeechBegin - parseInt(SPEECH_BEGIN_MARGIN), parseInt(ESR_SKIP_FRAME));
                    m_iVADState = ESVadStatus.ESVAD_ACTIVE;
                    m_iSpeechEnd = Math.min(m_iSpeechBegin + parseInt(SPEECH_END_MARGIN), m_iFrameEnd);
                    m_iSpeechEnd2 = m_iSpeechBegin;
                    a = Math.max(m_iSpeechBegin, parseInt(ESR_SKIP_FRAME));
                    m_s32EMax = 0
                } else {
                    m_s32ESil = 0;
                    m_iVADState = ESVadStatus.ESVAD_SILENCE;
                    m_iFrameHead++
                }
                break;
            case ESVadStatus.ESVAD_ACTIVE:
                g = m_pFrameEnergy[m_iFrameHead % ESR_FRAME_MAXNUM];
                if (g < m_s32ELow) {
                    m_iVADState = ESVadStatus.ESVAD_CHECK_END;
                    m_iFrameCheck = m_iFrameHead + 1
                } else {
                    m_s32EMax = Math.max(m_s32EMax, g);
                    if (m_s32EMax > (m_s32EHigh * ESR_MAX_EHIGH_COEFF)) {
                        g = simple_table_ln(m_s32EMax / ESR_MAX_EHIGH_COEFF, -10) >> 14;
                        g -= 9 << 8;
                        g = (g * g) >> 12;
                        g += 32 << 4;
                        g = (m_s32EMax / (parseInt(ESR_MAX_EHIGH_COEFF) * 16)) * g / 720;
                        g = simple_table_ln(g, -10) >> 14;
                        g -= 9 << 8;
                        g = (g * g) >> 12;
                        g += 32 << 4;
                        g = (m_s32EMax / (parseInt(ESR_MAX_EHIGH_COEFF) * 16)) * g / 720;
                        m_s32ESil = g;
                        g = m_s32ESil + 200;
                        m_s32ELow = (g) * 20 / parseInt(((simple_table_ln(g, 0) + ESR_MATH_10LN2) >> 18) - (4 << 4));
                        m_s32ELow <<= 5;
                        m_s32ELow -= 200;
                        g = parseInt((simple_table_ln(m_s32ESil, 0) + ESR_MATH_10LN2) >> 14);
                        f = ((g - (9 << 8)) * (g - (9 << 8))) >> 12;
                        f += (32 << 4);
                        m_s32EHigh = (m_s32ESil * (720 / 2) / f) << 5
                    }
                    m_iFrameHead++
                }
                m_iSpeechEnd = Math.min(m_iFrameHead + parseInt(SPEECH_END_MARGIN), m_iFrameEnd);
                m_iSpeechEnd2 = m_iFrameHead;
                break;
            case ESVadStatus.ESVAD_CHECK_END:
                m_iSpeechEnd = Math.min(m_iFrameHead + parseInt(SPEECH_END_MARGIN), m_iFrameEnd);
                m_iSpeechEnd2 = m_iFrameHead;
                d = m_iFrameEnd - m_iFrameCheck;
                if (d < m_nCheckEndFrame) {
                    return
                }
                if (CheckEngery(m_s32EHigh, parseInt(EHIGH_ENDVALID_THRESH), m_nCheckEndFrame)) {
                    log("local vad check end!!!!" + CheckEngery(m_s32EHigh, parseInt(EHIGH_ENDVALID_THRESH), m_nCheckEndFrame));
                    m_iFrameHead++;
                    m_iVADState = ESVadStatus.ESVAD_ACTIVE;
                    m_nCheckEndFrame = parseInt(EHIGH_CHKEND_FRAMENUM_SHORT)
                } else {
                    m_iVADState = ESVadStatus.ESVAD_INACTIVE;
                    speechEnd = true;
                    log("local vad check end!!!!");
                    postMessage({command: "esvad", message: "end"});
                    m_iFrameCheck = m_iFrameHead + 1;
                    m_s32ESil = 0;
                    if (m_iSpeechEnd - m_iSpeechBegin < MINIMUM_SPEECH_FRAMENUM + SPEECH_END_MARGIN) {
                        m_iSpeechBegin = 0;
                        m_iSpeechEnd = 0;
                        m_iVADState = ESVadStatus.ESVAD_SILENCE
                    }
                    return
                }
                break;
            case ESVadStatus.ESVAD_INACTIVE:
                return
        }
    }
};