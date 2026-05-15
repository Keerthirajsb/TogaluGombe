package com.togalugombe.app.data.local

import com.togalugombe.app.data.local.entity.*

object DatabaseSeeder {

    fun getPlays() = listOf(
        PlayEntity(1, "Ramayana", "ರಾಮಾಯಣ", "The epic tale of Lord Rama", "ಶ್ರೀ ರಾಮನ ಮಹಾಕಾವ್ಯ", "play_ramayana", 5),
        PlayEntity(2, "Mahabharata", "ಮಹಾಭಾರತ", "The great war of Kurukshetra", "ಕುರುಕ್ಷೇತ್ರ ಮಹಾಯುದ್ಧ", "play_mahabharata", 5),
        PlayEntity(3, "Krishna Leela", "ಕೃಷ್ಣ ಲೀಲಾ", "Divine plays of Lord Krishna", "ಶ್ರೀ ಕೃಷ್ಣನ ದಿವ್ಯ ಲೀಲೆಗಳು", "play_krishna", 4)
    )

    fun getScenes() = listOf(
        // Ramayana scenes
        SceneEntity(1, 1, 1, "Rama's Birth", "ರಾಮನ ಜನನ", "King Dasharatha performs a yagna. Rama is born as the eldest prince of Ayodhya.", "ದಶರಥ ರಾಜ ಯಜ್ಞ ಮಾಡುತ್ತಾನೆ. ರಾಮ ಅಯೋಧ್ಯೆಯ ಹಿರಿಯ ರಾಜಕುಮಾರನಾಗಿ ಜನಿಸುತ್ತಾನೆ.", "Rama, Dasharatha, Kausalya", "scene_rama_birth"),
        SceneEntity(2, 1, 2, "Sita Swayamvara", "ಸೀತಾ ಸ್ವಯಂವರ", "Rama breaks the divine bow of Shiva and wins Sita's hand in marriage.", "ರಾಮ ಶಿವನ ದಿವ್ಯ ಧನುಸ್ಸನ್ನು ಮುರಿದು ಸೀತೆಯನ್ನು ವಿವಾಹವಾಗುತ್ತಾನೆ.", "Rama, Sita, Janaka", "scene_swayamvara"),
        SceneEntity(3, 1, 3, "Exile to Forest", "ವನವಾಸ", "Rama, Sita, and Lakshmana leave Ayodhya for 14 years of exile.", "ರಾಮ, ಸೀತಾ ಮತ್ತು ಲಕ್ಷ್ಮಣ 14 ವರ್ಷ ವನವಾಸಕ್ಕೆ ಹೊರಡುತ್ತಾರೆ.", "Rama, Sita, Lakshmana", "scene_exile"),
        SceneEntity(4, 1, 4, "Sita's Abduction", "ಸೀತಾಪಹರಣ", "Ravana kidnaps Sita using a golden deer as distraction.", "ರಾವಣ ಚಿನ್ನದ ಜಿಂಕೆಯ ಮೋಸದಿಂದ ಸೀತೆಯನ್ನು ಅಪಹರಿಸುತ್ತಾನೆ.", "Ravana, Sita, Maricha", "scene_abduction"),
        SceneEntity(5, 1, 5, "Battle & Victory", "ಯುದ್ಧ ಮತ್ತು ವಿಜಯ", "Rama defeats Ravana in the great battle and rescues Sita.", "ರಾಮ ಮಹಾಯುದ್ಧದಲ್ಲಿ ರಾವಣನನ್ನು ಸೋಲಿಸಿ ಸೀತೆಯನ್ನು ರಕ್ಷಿಸುತ್ತಾನೆ.", "Rama, Ravana, Hanuman", "scene_battle"),
        // Mahabharata scenes
        SceneEntity(6, 2, 1, "Dice Game", "ಪಗಡೆ ಆಟ", "Yudhishthira loses everything in the game of dice to Shakuni.", "ಯುಧಿಷ್ಠಿರ ಶಕುನಿಯ ಪಗಡೆ ಆಟದಲ್ಲಿ ಎಲ್ಲವನ್ನೂ ಕಳೆದುಕೊಳ್ಳುತ್ತಾನೆ.", "Yudhishthira, Shakuni, Draupadi", "scene_dice"),
        SceneEntity(7, 2, 2, "Draupadi's Honour", "ದ್ರೌಪದಿಯ ಮಾನ", "Krishna saves Draupadi's honour in the Kaurava court.", "ಕೃಷ್ಣ ಕೌರವ ಸಭೆಯಲ್ಲಿ ದ್ರೌಪದಿಯ ಮಾನ ರಕ್ಷಿಸುತ್ತಾನೆ.", "Draupadi, Krishna, Dushasana", "scene_draupadi"),
        SceneEntity(8, 2, 3, "Bhagavad Gita", "ಭಗವದ್ಗೀತೆ", "Krishna reveals the Gita to Arjuna on the battlefield.", "ಕೃಷ್ಣ ಯುದ್ಧಭೂಮಿಯಲ್ಲಿ ಅರ್ಜುನನಿಗೆ ಗೀತೆಯನ್ನು ಉಪದೇಶಿಸುತ್ತಾನೆ.", "Krishna, Arjuna", "scene_gita"),
        SceneEntity(9, 2, 4, "Fall of Bhishma", "ಭೀಷ್ಮನ ಪತನ", "Bhishma falls on the bed of arrows on the tenth day.", "ಭೀಷ್ಮ ಹತ್ತನೇ ದಿನ ಬಾಣಗಳ ಹಾಸಿಗೆಯ ಮೇಲೆ ಬೀಳುತ್ತಾನೆ.", "Bhishma, Arjuna, Shikhandi", "scene_bhishma"),
        SceneEntity(10, 2, 5, "The Great War Ends", "ಮಹಾಯುದ್ಧದ ಅಂತ್ಯ", "Duryodhana falls. Pandavas win the war of Kurukshetra.", "ದುರ್ಯೋಧನ ಬೀಳುತ್ತಾನೆ. ಪಾಂಡಪರು ಕುರುಕ್ಷೇತ್ರ ಯುದ್ಧ ಗೆಲ್ಲುತ್ತಾರೆ.", "Duryodhana, Bhima, Yudhishthira", "scene_war_end"),
        // Krishna Leela scenes
        SceneEntity(11, 3, 1, "Birth of Krishna", "ಕೃಷ್ಣನ ಜನನ", "Krishna is born in Mathura prison and taken to Gokula.", "ಕೃಷ್ಣ ಮಥುರಾ ಸೆರೆಮನೆಯಲ್ಲಿ ಜನಿಸಿ ಗೋಕುಲಕ್ಕೆ ತೆಗೆದುಕೊಂಡು ಹೋಗಲ್ಪಡುತ್ತಾನೆ.", "Krishna, Vasudeva, Devaki", "scene_krishna_birth"),
        SceneEntity(12, 3, 2, "Butter Thief", "ಬೆಣ್ಣೆ ಕಳ್ಳ", "Young Krishna steals butter from the gopis of Vrindavan.", "ಬಾಲಕೃಷ್ಣ ವೃಂದಾವನದ ಗೋಪಿಯರ ಬೆಣ್ಣೆ ಕದಿಯುತ್ತಾನೆ.", "Krishna, Yashoda, Gopis", "scene_butter"),
        SceneEntity(13, 3, 3, "Kalinga Narthana", "ಕಾಳಿಂಗ ನರ್ತನ", "Krishna dances on the serpent Kaliya to save the villagers.", "ಕೃಷ್ಣ ಗ್ರಾಮಸ್ಥರನ್ನು ರಕ್ಷಿಸಲು ಕಾಳಿಯ ಸರ್ಪದ ಮೇಲೆ ನೃತ್ಯ ಮಾಡುತ್ತಾನೆ.", "Krishna, Kaliya", "scene_kalinga"),
        SceneEntity(14, 3, 4, "Govardhan Leela", "ಗೋವರ್ಧನ ಲೀಲೆ", "Krishna lifts Mount Govardhan to protect people from Indra's storm.", "ಕೃಷ್ಣ ಇಂದ್ರನ ಬಿರುಗಾಳಿಯಿಂದ ಜನರನ್ನು ರಕ್ಷಿಸಲು ಗೋವರ್ಧನ ಬೆಟ್ಟ ಎತ್ತುತ್ತಾನೆ.", "Krishna, Indra, Villagers", "scene_govardhan")
    )

    fun getPuppets() = listOf(
        PuppetEntity(1, "Rama", "ರಾಮ", "The seventh avatar of Vishnu, prince of Ayodhya and hero of Ramayana.", "ವಿಷ್ಣುವಿನ ಏಳನೇ ಅವತಾರ, ಅಯೋಧ್ಯೆಯ ರಾಜಕುಮಾರ.", "Divine bow mastery, Dharma", "ದಿವ್ಯ ಧನುರ್ವಿದ್ಯೆ, ಧರ್ಮ", "Righteousness and duty", "ಧರ್ಮ ಮತ್ತು ಕರ್ತವ್ಯ", "puppet_rama", "Ramayana", "hero"),
        PuppetEntity(2, "Sita", "ಸೀತಾ", "Daughter of earth, wife of Rama, symbol of purity and devotion.", "ಭೂಮಿಯ ಮಗಳು, ರಾಮನ ಪತ್ನಿ, ಪಾವಿತ್ರ್ಯ ಮತ್ತು ಭಕ್ತಿಯ ಸಂಕೇತ.", "Purity, Devotion", "ಪಾವಿತ್ರ್ಯ, ಭಕ್ತಿ", "Feminine strength and sacrifice", "ಸ್ತ್ರೀ ಶಕ್ತಿ ಮತ್ತು ತ್ಯಾಗ", "puppet_sita", "Ramayana", "hero"),
        PuppetEntity(3, "Ravana", "ರಾವಣ", "Ten-headed king of Lanka, a great scholar turned villain.", "ಲಂಕೆಯ ದಶಮುಖ ರಾಜ, ಮಹಾ ವಿದ್ವಾಂಸ.", "Ten heads of knowledge, Immortality boon", "ಹತ್ತು ತಲೆಯ ಜ್ಞಾನ, ಅಮರತ್ವ ವರ", "Ego and the fall of the learned", "ಅಹಂಕಾರ ಮತ್ತು ವಿದ್ವಾಂಸರ ಪತನ", "puppet_ravana", "Ramayana", "villain"),
        PuppetEntity(4, "Hanuman", "ಹನುಮಂತ", "The mighty monkey god, devoted servant of Lord Rama.", "ಮಹಾಬಲಶಾಲಿ ವಾನರ ದೇವ, ರಾಮನ ಭಕ್ತ.", "Flying, Immense strength, Shape-shifting", "ಹಾರಾಟ, ಅಪಾರ ಶಕ್ತಿ, ರೂಪ ಬದಲಾವಣೆ", "Devotion and selfless service", "ಭಕ್ತಿ ಮತ್ತು ನಿಸ್ವಾರ್ಥ ಸೇವೆ", "puppet_hanuman", "Ramayana", "deity"),
        PuppetEntity(5, "Arjuna", "ಅರ್ಜುನ", "The greatest archer, third Pandava prince.", "ಮಹಾ ಧನುರ್ಧಾರಿ, ಮೂರನೇ ಪಾಂಡವ.", "Gandiva bow, Divine weapons", "ಗಾಂಡೀವ ಧನುಸ್ಸು, ದಿವ್ಯಾಸ್ತ್ರಗಳು", "Warrior's dilemma and duty", "ಯೋಧನ ಧರ್ಮಸಂಕಟ ಮತ್ತು ಕರ್ತವ್ಯ", "puppet_arjuna", "Mahabharata", "hero"),
        PuppetEntity(6, "Krishna", "ಕೃಷ್ಣ", "The eighth avatar of Vishnu, divine guide and protector.", "ವಿಷ್ಣುವಿನ ಎಂಟನೇ ಅವತಾರ, ದಿವ್ಯ ಮಾರ್ಗದರ್ಶಿ.", "Sudarshana Chakra, Divine wisdom", "ಸುದರ್ಶನ ಚಕ್ರ, ದಿವ್ಯ ಜ್ಞಾನ", "Divine love and cosmic truth", "ದಿವ್ಯ ಪ್ರೀತಿ ಮತ್ತು ವಿಶ್ವ ಸತ್ಯ", "puppet_krishna", "Krishna Leela", "deity"),
        PuppetEntity(7, "Draupadi", "ದ್ರೌಪದಿ", "Born from fire, queen of the five Pandavas.", "ಅಗ್ನಿಯಿಂದ ಜನಿಸಿದ, ಐದು ಪಾಂಡವರ ರಾಣಿ.", "Fire-born resilience", "ಅಗ್ನಿಜನ್ಯ ಸ್ಥಿತಿಸ್ಥಾಪಕತ್ವ", "Justice and feminine power", "ನ್ಯಾಯ ಮತ್ತು ಸ್ತ್ರೀ ಶಕ್ತಿ", "puppet_draupadi", "Mahabharata", "hero"),
        PuppetEntity(8, "Bhishma", "ಭೀಷ್ಮ", "The grand patriarch who took a vow of celibacy for his father.", "ತಂದೆಗಾಗಿ ಬ್ರಹ್ಮಚರ್ಯ ವ್ರತ ತೆಗೆದುಕೊಂಡ ಮಹಾಪಿತಾಮಹ.", "Ichha Mrityu - death at will", "ಇಚ್ಛಾಮೃತ್ಯು", "Sacrifice and unwavering vow", "ತ್ಯಾಗ ಮತ್ತು ಅಚಲ ಪ್ರತಿಜ್ಞೆ", "puppet_bhishma", "Mahabharata", "support")
    )

    fun getArtists() = listOf(
        ArtistEntity(1, "Shri Gunduraju", "A master puppeteer from Bellary with 40+ years of experience in Togalu Gombeyaata.", "Bellary, Karnataka", "+91 98450 12345", "Traditional Ramayana puppets", "artist_gunduraju"),
        ArtistEntity(2, "Smt. Kamala Devi", "A renowned artist keeping the tradition alive by training the next generation of puppeteers.", "Chitradurga, Karnataka", "+91 98450 67890", "Mahabharata characters", "artist_kamala"),
        ArtistEntity(3, "Shri Basavaraju", "Award-winning puppeteer known for crafting intricate leather puppets with detailed perforations.", "Dharwad, Karnataka", "+91 98450 11223", "Miniature decorative puppets", "artist_basavaraju")
    )

    fun getWorkshops() = listOf(
        WorkshopEntity(1, 1, "Leather Puppet Making Basics", "Learn the art of cutting and perforating leather to create shadow puppets.", "2026-06-15", "Bellary Art Center", 500.0, 20),
        WorkshopEntity(2, 2, "Storytelling with Puppets", "Master the art of narrating epic tales using traditional puppets.", "2026-06-22", "Chitradurga Cultural Hub", 300.0, 15),
        WorkshopEntity(3, 3, "Advanced Puppet Crafting", "Create detailed miniature puppets with intricate perforation patterns.", "2026-07-01", "Dharwad Heritage Center", 800.0, 10),
        WorkshopEntity(4, 1, "Weekend Puppet Camp", "A 2-day intensive workshop covering puppet making and performance.", "2026-07-10", "Bellary Art Center", 1200.0, 12)
    )

    fun getHistoryVideos() = listOf(
        HistoryVideoEntity(1, "Selecting the Leather", "ಚರ್ಮ ಆಯ್ಕೆ", "How artisans select and prepare goat or deer hide for puppets.", "ಕಲಾವಿದರು ಬೊಂಬೆಗಳಿಗಾಗಿ ಮೇಕೆ ಅಥವಾ ಜಿಂಕೆ ಚರ್ಮವನ್ನು ಆಯ್ಕೆ ಮಾಡುವ ವಿಧಾನ.", "", "thumb_leather", "leather_curing", 180),
        HistoryVideoEntity(2, "Curing & Treating", "ಸಂಸ್ಕರಣೆ", "The traditional process of curing leather using natural methods.", "ನೈಸರ್ಗಿಕ ವಿಧಾನಗಳಿಂದ ಚರ್ಮ ಸಂಸ್ಕರಣೆಯ ಸಾಂಪ್ರದಾಯಿಕ ಪ್ರಕ್ರಿಯೆ.", "", "thumb_curing", "leather_curing", 240),
        HistoryVideoEntity(3, "Natural Dyeing", "ನೈಸರ್ಗಿಕ ಬಣ್ಣ", "Using turmeric, indigo, and other natural dyes to color the leather.", "ಅರಿಶಿನ, ನೀಲಿ ಮತ್ತು ಇತರ ನೈಸರ್ಗಿಕ ಬಣ್ಣಗಳಿಂದ ಚರ್ಮಕ್ಕೆ ಬಣ್ಣ ಹಾಕುವುದು.", "", "thumb_dyeing", "dyeing", 200),
        HistoryVideoEntity(4, "Cutting & Shaping", "ಕತ್ತರಿಸುವಿಕೆ", "Precision cutting of leather into puppet shapes and forms.", "ಚರ್ಮವನ್ನು ಬೊಂಬೆ ಆಕಾರಗಳಲ್ಲಿ ನಿಖರವಾಗಿ ಕತ್ತರಿಸುವುದು.", "", "thumb_cutting", "cutting", 160),
        HistoryVideoEntity(5, "Perforation Art", "ರಂಧ್ರ ಕಲೆ", "Creating the intricate perforation patterns that define shadow puppet art.", "ನೆರಳು ಬೊಂಬೆ ಕಲೆಯನ್ನು ವ್ಯಾಖ್ಯಾನಿಸುವ ಸಂಕೀರ್ಣ ರಂಧ್ರ ಮಾದರಿಗಳ ರಚನೆ.", "", "thumb_perforation", "cutting", 220),
        HistoryVideoEntity(6, "Assembly & Joints", "ಜೋಡಣೆ", "Joining puppet parts with thread and bamboo sticks for movement.", "ಚಲನೆಗಾಗಿ ದಾರ ಮತ್ತು ಬಿದಿರು ಕಡ್ಡಿಗಳಿಂದ ಬೊಂಬೆ ಭಾಗಗಳನ್ನು ಜೋಡಿಸುವುದು.", "", "thumb_assembly", "assembly", 190)
    )
}
